package com.example.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.example.activity.StockDetailActivity;
import com.example.adapter.StockListMultiAdapter;
import com.example.databinding.FragmentStockListBinding;
import com.example.entity.LookupEntity;
import com.example.ui.StockListView;
import com.example.viewmodel.StockListViewModel;


public class StockListMultiFragment extends BaseFragment<StockListViewModel, FragmentStockListBinding> implements StockListView
{
	@Override
	public StockListViewModel setupViewModel()
	{
		StockListViewModel viewModel = ViewModelProviders.of(this).get(StockListViewModel.class);
		getLifecycle().addObserver(viewModel);
		return viewModel;
	}


	@Override
	public FragmentStockListBinding inflateBindingLayout(@NonNull LayoutInflater inflater)
	{
		return FragmentStockListBinding.inflate(inflater);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		getBinding().executePendingBindings(); // set layout manager in recycler via binding adapter
		setupAdapter();
	}


	@Override
	public void onItemClick(View view, int position, long id, int viewType)
	{
	}


	@Override
	public void onItemLongClick(View view, int position, long id, int viewType)
	{
	}


	@Override
	public void onItemClick(LookupEntity lookup)
	{
		startStockDetailActivity(lookup.getSymbol());
//		getViewModel().addItem();
//		mAdapter.notifyDataSetChanged();
	}


	@Override
	public boolean onItemLongClick(LookupEntity lookup)
	{
		getViewModel().updateItem(lookup);
		return true;
	}


	private void setupAdapter()
	{
		StockListMultiAdapter adapter = new StockListMultiAdapter(this, getViewModel());
		getBinding().fragmentStockListRecycler.setAdapter(adapter);
	}


	private void startStockDetailActivity(String symbol)
	{
		Intent intent = StockDetailActivity.newIntent(getActivity(), symbol);
		getActivity().startActivity(intent);
	}
}
