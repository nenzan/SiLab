package id.compunerd.silab.fragment;


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

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import id.compunerd.silab.R;
import id.compunerd.silab.adapter.RecyclerAdapter;
import id.compunerd.silab.model.Item;
import id.compunerd.silab.model.ResultItem;
import id.compunerd.silab.rest.ApiClient;
import id.compunerd.silab.rest.ApiInterface;
import id.compunerd.silab.rest.UtilsApi;
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
    private RecyclerAdapter rAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);

        shimmerContainer = (ShimmerFrameLayout) v.findViewById(R.id.shimmerViewContainer);
        mApiService = UtilsApi.getAPIService();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mApiService.getDataPengujian().enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()){
                    resultItem = (ArrayList<ResultItem>) response.body().getSuccess();
                    recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
                    rAdapter = new RecyclerAdapter(resultItem);
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
