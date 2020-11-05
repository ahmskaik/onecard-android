package com.nawasoft.oneapp.offers.mapfragment;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nawasoft.base.BaseFragment;
import com.nawasoft.datalayer.model.City;
import com.nawasoft.datalayer.model.OfferMarker;
import com.nawasoft.oneapp.R;
import com.nawasoft.oneapp.callbacks.OnMarkerClickCallback;
import com.nawasoft.oneapp.offers.mapfragment.mvp.MapMVP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class OfferMapFragment extends BaseFragment implements MapMVP.View, OnMapReadyCallback,
        GoogleMap.OnCameraIdleListener {

    @Inject
    MapMVP.Presenter presenter;

    public static String MAP_CATEGORY_ID = "category_id";
    public static String MAP_COUNTRY_ID = "country_id";
    public static String MAP_CITY_ID = "city_id";

    private GoogleMap googleMap;

    private final int LOCATION_REQUEST_CODE = 131;
    private String[] locationPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private Double longitude = null, latitude = null;
    private String categoryId = "";
    private String countryId = "", cityId = "";


    private List<OfferMarker> offerMarkers = new ArrayList<>();
    private OnMarkerClickCallback callback;

    public static OfferMapFragment getInstance(OnMarkerClickCallback clickCallback, String categoryId,
                                               String countryId, String cityId) {
        Bundle bundle = new Bundle();
        bundle.putString(OfferMapFragment.MAP_CATEGORY_ID, categoryId);
        bundle.putString(OfferMapFragment.MAP_COUNTRY_ID, countryId);
        bundle.putString(OfferMapFragment.MAP_CITY_ID, cityId);
        OfferMapFragment fragment = new OfferMapFragment(clickCallback);
        fragment.setArguments(bundle);
        return fragment;
    }

    public OfferMapFragment(OnMarkerClickCallback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment_layout, container, false);
        categoryId = getArguments().getString(MAP_CATEGORY_ID, "");
        countryId = getArguments().getString(MAP_COUNTRY_ID, "");
        cityId = getArguments().getString(MAP_CITY_ID, "");
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        supportMapFragment.getMapAsync(this);
        presenter.setView(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelCalls();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnCameraIdleListener(this);
        this.googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style)
        );
        this.googleMap.setOnMarkerClickListener(marker -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(OfferDialog.OFFER, (OfferMarker) marker.getTag());
            DialogFragment dialogFragment = new OfferDialog(callback);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getChildFragmentManager(), "offer_dialog");
            return false;
        });
        getLocation();
    }

    @Override
    public void onCameraIdle() {
        latitude = googleMap.getCameraPosition().target.latitude;
        longitude = googleMap.getCameraPosition().target.longitude;
        presenter.getOffers();
    }

    private void focusCameraOn(Double latitude, Double longitude) {
        if (longitude == null || latitude == null || googleMap == null)
            return;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10f));
    }


    @AfterPermissionGranted(LOCATION_REQUEST_CODE)
    private void getLocation() {
        if (!EasyPermissions.hasPermissions(getContext(), locationPermissions)) {
            EasyPermissions.requestPermissions(
                    this,
                    "Location permissions",
                    LOCATION_REQUEST_CODE,
                    locationPermissions
            );
        } else {
            this.googleMap.setMyLocationEnabled(true);
            this.googleMap.setOnMyLocationChangeListener(location -> {
                this.latitude = location.getLatitude();
                this.longitude = location.getLongitude();
                focusCameraOn(latitude, longitude);
                googleMap.setOnMyLocationChangeListener(null);
            });
        }
    }

    private void refreshMap() {
        if (googleMap != null) {
            googleMap.clear();
            offerMarkers.clear();
            presenter.getOffers();
        }
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId.equalsIgnoreCase(this.categoryId) ? "" : categoryId;
        refreshMap();
    }

    public void setCountryAndCity(String countryId, City city) {
        this.countryId = countryId;
        this.cityId = city.getCityId();
        if (city.hasLatLang()) {
            focusCameraOn(Double.valueOf(city.getLatitude()), Double.valueOf(city.getLongitude()));
        } else
            System.out.println("empty city");
        refreshMap();
    }

    // view methods
    @Override
    public String getLatitude() {
        return latitude != null ? String.valueOf(latitude) : "";
    }

    @Override
    public String getLongitude() {
        return longitude != null ? String.valueOf(longitude) : "";
    }

    @Override
    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public void addOfferMarker(OfferMarker offerMarker) {
        for (OfferMarker marker : offerMarkers) {
            if (marker.getOfferId().equalsIgnoreCase(offerMarker.getOfferId()))
                return;
        }
        offerMarkers.add(offerMarker);
        if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(
                    new LatLng(Double.valueOf(offerMarker.getLatitude()), Double.valueOf(offerMarker.getLongitude()))
                    ).icon(getMarkerIcon())
            ).setTag(offerMarker);
        }
    }

    @Override
    public String getCountryId() {
        return countryId;
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    private BitmapDescriptor getMarkerIcon() {
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_marker);
        Bitmap bitmap = drawable.getBitmap();
        return BitmapDescriptorFactory.fromBitmap(
                Bitmap.createScaledBitmap(bitmap, 100, 100, false)
        );
    }
}
