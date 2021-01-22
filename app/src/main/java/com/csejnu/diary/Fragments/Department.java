package com.csejnu.diary.Fragments;import android.os.Bundle;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import androidx.annotation.Nullable;import androidx.cardview.widget.CardView;import androidx.fragment.app.Fragment;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import androidx.viewpager.widget.ViewPager;import com.csejnu.diary.Adapter.TabAdapter;import com.csejnu.diary.R;import com.google.android.material.tabs.TabLayout;import org.jetbrains.annotations.NotNull;import static com.csejnu.diary.MainActivity.toolbarMain;/** * Created by jhm69 */public class Department extends Fragment {    String dept = "কম্পিউটার সায়েন্স এন্ড ইঞ্জিনিয়ারিং বিভাগ";    int postCount = 0;    LinearLayoutManager layoutManager;    RecyclerView recyclerView;    private CardView request_alert;    private TextView request_alert_text;    private TabAdapter adapter;    private TabLayout tabLayout;    private ViewPager viewPager;    public Department(String dept) {        this.dept = dept;    }    @Nullable    @Override    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        return inflater.inflate(R.layout.frag_dept, container, false);    }    @Override    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        tabLayout = getView().findViewById(R.id.tabLayout);        viewPager = getView().findViewById(R.id.view_pager);        adapter = new TabAdapter(getActivity().getSupportFragmentManager(), getActivity());        //List<String> titleList = Arrays.asList(getResources().getStringArray(R.array.titles));        if (dept.equals("intro")) {            adapter.addFragment(new LandingPage(), "প্রচ্ছদ");        } else {            toolbarMain.setSubtitle(dept);            adapter.addFragment(new Dashboard(dept, 1), "ডিপার্টমেন্ট");        }        adapter.addFragment(new Dashboard(dept, 2), "পছন্দের");        adapter.addFragment(new FragmentNotices(), "বিজ্ঞপ্তি");        viewPager.setAdapter(adapter);        for (int i = 0; i < tabLayout.getTabCount(); i++) {            TabLayout.Tab tab = tabLayout.getTabAt(i);            assert tab != null;            tab.setCustomView(null);            tab.setCustomView(adapter.getTabView(i));        }        tabLayout.setupWithViewPager(viewPager);        highLightCurrentTab(0); // for initial selected tab view        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {            @Override            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {            }            @Override            public void onPageSelected(int position) {                highLightCurrentTab(position);            }            @Override            public void onPageScrollStateChanged(int state) {            }        });    }    private void highLightCurrentTab(int position) {        for (int i = 0; i < tabLayout.getTabCount(); i++) {            TabLayout.Tab tab = tabLayout.getTabAt(i);            assert tab != null;            tab.setCustomView(null);            tab.setCustomView(adapter.getTabView(i));        }        TabLayout.Tab tab = tabLayout.getTabAt(position);        assert tab != null;        tab.setCustomView(null);        tab.setCustomView(adapter.getSelectedTabView(position));    }}