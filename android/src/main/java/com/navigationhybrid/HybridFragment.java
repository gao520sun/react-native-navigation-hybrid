package com.navigationhybrid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import me.listenzz.navigation.AwesomeFragment;
import me.listenzz.navigation.FragmentHelper;

import static com.navigationhybrid.Constants.ARG_PROPS;
import static com.navigationhybrid.Constants.ARG_SCENE_ID;

/**
 * Created by Listen on 2018/1/15.
 */

public class HybridFragment extends AwesomeFragment {

    private final ReactBridgeManager bridgeManager = ReactBridgeManager.instance;

    private Garden garden;

    @Override
    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = super.onGetLayoutInflater(savedInstanceState);
        garden = new Garden(this);
        return layoutInflater;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        garden.configTopBar();
    }

    public Garden getGarden() {
        return garden;
    }

    @Override
    protected boolean shouldHideBackButton() {
        return garden.backBackHidden;
    }

    @Override
    protected boolean backInteractive() {
        return garden.backInteractive;
    }

    @Override
    protected boolean hidesBottomBarWhenPushed() {
        return garden.hidesBottomBarWhenPushed;
    }

    @Override
    protected Toolbar onCreateToolbar(View parent) {
        if (garden.topBarHidden) {
            return null;
        }
        return super.onCreateToolbar(parent);
    }

    @NonNull
    public ReactBridgeManager getReactBridgeManager() {
        return bridgeManager;
    }

    public Bundle getOptions() {
        Bundle args = FragmentHelper.getArguments(this);
        return args.getBundle(Constants.ARG_OPTIONS);
    }

    public void setOptions(Bundle options) {
        Bundle args = getArguments();
        if (args != null) {
            args.putBundle(Constants.ARG_OPTIONS, options);
            setArguments(args);
        }
    }

    public Bundle getProps() {
        Bundle args = FragmentHelper.getArguments(this);
        Bundle initialProps = args.getBundle(ARG_PROPS);
        if (initialProps == null) {
            initialProps = new Bundle();
        }
        initialProps.putString(ARG_SCENE_ID, getSceneId());
        return initialProps;
    }

}
