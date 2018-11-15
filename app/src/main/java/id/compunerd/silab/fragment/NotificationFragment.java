package id.compunerd.silab.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import id.compunerd.silab.MainActivity;
import id.compunerd.silab.R;
import id.compunerd.silab.adapter.RecyclerAdapterNotification;
import id.compunerd.silab.model.Item;
import id.compunerd.silab.model.ResultItem;
import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
import id.compunerd.silab.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    public NotificationFragment() {
        // Required empty public constructor
    }

    ShimmerFrameLayout shimmerContainer;
    ApiInterface mApiService;
    private ArrayList<ResultItem> resultItem;
    private RecyclerView recyclerView;
    private RecyclerAdapterNotification rAdapter;
    SharedPrefManager sharedPrefManager;
    String idPerusahaan = "PR00000001";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        shimmerContainer = (ShimmerFrameLayout) v.findViewById(R.id.shimmerViewContainer);
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(getActivity());

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (idPerusahaan.isEmpty()) {
            Toast.makeText(getActivity(), R.string.please_login_first, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        } else {
            idPerusahaan = sharedPrefManager.getSPIdPerusahaan();
            mApiService.getDataPengujian().enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    if (response.isSuccessful()) {
                        resultItem = (ArrayList<ResultItem>) response.body().getSuccess();
                        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
                        rAdapter = new RecyclerAdapterNotification(resultItem);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(rAdapter);
                        shimmerContainer.stopShimmer();
                        shimmerContainer.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerContainer.startShimmer();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
