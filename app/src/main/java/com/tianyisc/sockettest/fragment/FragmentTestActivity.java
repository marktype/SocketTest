package com.tianyisc.sockettest.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tianyisc.sockettest.R;

public class FragmentTestActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup mRadioGroup;
    private RadioButton mOneBtn,mTwoBtn,mThreeBtn;
    private BlankFragment tabOneFragment;
    private BlankFragment2 tabTwoFragment;
    private BlankFragment3 tabThreeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        mRadioGroup = (RadioGroup) findViewById(R.id.raidoGroupTab);
        mRadioGroup.setOnCheckedChangeListener(this);
        tabOneFragment = new BlankFragment();

        //进入时默认选中
        getFragmentManager().beginTransaction().add(R.id.fragment_tab_content,tabOneFragment).commit();
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        hideFragments(ft);  //隐藏fragment
        switch (checkedId){
            case R.id.raidoTab1:
                if (tabOneFragment == null){
                    tabOneFragment = new BlankFragment();
                    ft.add(R.id.fragment_tab_content,tabOneFragment);
                }else {
                    ft.show(tabOneFragment);
                }
                break;
            case R.id.raidoTab2:
                if (tabTwoFragment == null){
                    tabTwoFragment = new BlankFragment2();
                    ft.add(R.id.fragment_tab_content,tabTwoFragment);
                }else {
                    ft.show(tabTwoFragment);
                }
                break;
            case R.id.raidoTab3:
                if (tabThreeFragment == null){
                    tabThreeFragment = new BlankFragment3();
                    ft.add(R.id.fragment_tab_content,tabThreeFragment);
                }else {
                    ft.show(tabThreeFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (tabOneFragment != null) {
            transaction.hide(tabOneFragment);
        }
        if (tabTwoFragment != null) {
            transaction.hide(tabTwoFragment);
        }
        if (tabThreeFragment != null) {
            transaction.hide(tabThreeFragment);
        }
    }
}
