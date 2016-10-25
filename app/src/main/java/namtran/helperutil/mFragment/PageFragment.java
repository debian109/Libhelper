/*
 * Copyright (C) 2014 Pixplicity
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package namtran.helperutil.mFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import namtran.helperutil.ActivityExample.CardPageActivity;
import namtran.helperutil.R;

public class PageFragment extends Fragment implements CardPageActivity.ViewPagerOnPageChangeListener {

    private static final String ARG_PAGE_NUMBER = "pageNumber";
    private TextView txtTest;

    public static PageFragment create(int pageNumber) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        txtTest = (TextView) rootView.findViewById(R.id.txtTest);
        final Bundle arguments = getArguments();
        if (arguments != null) {
            txtTest.setText("There is position : " + (arguments.getInt(ARG_PAGE_NUMBER) + 1));
        }
        return rootView;
    }

    @Override
    public void onPageScrolled(float positionOffset, int positionOffsetPixels) {
        Log.d("Interface","Position Center");
        if (txtTest != null){
            Log.d("Interface","OK");
        }else {
            Log.d("Interface","Null");
        }
    }

    @Override
    public void onPageScrolled(boolean isNext, float positionOffset, int positionOffsetPixels) {
        if (isNext){
            Log.d("Interface","Position next");
            if (txtTest != null){
                txtTest.setText("There is position : " + positionOffset);
            }else {
                Log.d("Interface","Null");
            }
        }else {
            Log.d("Interface","Position previous");
            if (txtTest != null){
                txtTest.setText("There is position : " + positionOffset);
            }else {
                Log.d("Interface","Null");
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (txtTest != null){
            txtTest.setText("There is position : " + position);
        }
    }
}
