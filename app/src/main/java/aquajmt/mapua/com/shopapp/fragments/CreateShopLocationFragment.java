package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import aquajmt.mapua.com.shopapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bryan on 7/18/2017.
 */

public class CreateShopLocationFragment extends Fragment {

    private Listener listener;

    private GoogleMap googleMap;
    private Marker marker;
    private LatLng markerPosition;

    @BindView(R.id.map_view)
    MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_shop_location, container, false);
        ButterKnife.bind(this, view);

        mapView.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();
        MapsInitializer.initialize(getActivity().getApplicationContext());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                CreateShopLocationFragment.this.googleMap = googleMap;
                googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) { }

                    @Override
                    public void onMarkerDrag(Marker marker) { }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        markerPosition = marker.getPosition();
                    }
                });

                listener.retrieveLocation(new Receiver() {
                    @Override
                    public void receive(float lat, float lng) {
                        markerPosition = new LatLng(lat, lng);
                        continueMapInit();
                    }

                    @Override
                    public void notInitialized() {
                        markerPosition = new LatLng(14.5995, 120.9842); // Manila location
                        continueMapInit();
                    }

                    private void continueMapInit() {
                        marker = googleMap.addMarker(new MarkerOptions()
                                .draggable(true).position(markerPosition)
                                .title("Your shop location"));

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(markerPosition).zoom(14).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement "
                    + CreateShopLocationFragment.class.getSimpleName() + "."
                    + Listener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_next)
    void btnNextOnClick() {
        float latitude = (float) markerPosition.latitude;
        float longitude = (float) markerPosition.longitude;

        listener.completedLocation(latitude, longitude);
    }

    public interface Listener {
        void completedLocation(float latitude, float longitude);
        void retrieveLocation(Receiver receiver);
    }

    public interface Receiver {
        void receive(float lat, float lng);
        void notInitialized();
    }
}
