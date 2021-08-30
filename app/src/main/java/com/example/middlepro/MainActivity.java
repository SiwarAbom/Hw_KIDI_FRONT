package com.example.middlepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class MainActivity extends AppCompatActivity {
    private PieChart pieChart;
    private PieChart pieChartKids;
    private PieChart pieChartTotal;
    private PieChart pieChartActivity;
    private RadioButton radioYear;
    private RadioButton radioMonth;
    private RadioButton radioWeek;
    int period = 3;
    BarChart barchart;

    private int[] yData={70,30};
    private String[] xData={"x","y"};

    private int[] yDataTotal;
    private String[] xDataTotal={"sport","space","art","ff"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on creat");

        //Initialize the radio button
        radioYear = (RadioButton)findViewById(R.id.radioYear);
        radioMonth = (RadioButton)findViewById(R.id.radioMonth);
        radioWeek = (RadioButton)findViewById(R.id.radioWeek);

        radioYear.performClick();
        radioYear.performClick();
        //Initialize the bottom navigation view
        //create bottom navigation view object
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);
        androidx.fragment.app.Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_fragment);
        NavController navControler = findNavController(navHostFragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navControler);
        //navigationView.setSelectedItemId(R.id.nav_home);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.backFragment:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        return true;
                    case R.id.coursesFragment:
                        fragment=new CoursesFragment();
                        break;
                    case R.id.leadersFragment:
                        fragment=new LeadersFragment();
                        break;
                    case R.id.usersFragment:
                        fragment=new UsersFragment();
                        break;
                    case R.id.moreFragment:
                        fragment=new MoreFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main,fragment).commit();
                return true;
            }
        });
        //radioYear.performClick();


        pieChartTotal = findViewById(R.id.activity_main_piechartTotal);
        pieChartTotal.setRotationEnabled(false);
        pieChartTotal.setUsePercentValues(false);
        pieChartTotal.setHoleRadius(0f);
        pieChartTotal.setTransparentCircleAlpha(0);
        pieChartTotal.getDescription().setEnabled(false);
        pieChartTotal.setHighlightPerTapEnabled(false);



        barchart = findViewById(R.id.barchart);
        adddata();
//on start
        //on revieon
        //addDataSetParents();
        getPrecParents();
        //addDataSetKids();
        getPrecParentsKid( );
        getTotal();
       // addDataSetActivity();
       // addDataSetTotal();
        getActivity();
        showBarChart();
        initBarChart();
        ArrayList<String> labvel = new ArrayList<String>();
        labvel.add("");
        labvel.add("1");
        labvel.add("2");
        labvel.add("3");
        labvel.add("4");
        labvel.add("5");
        labvel.add("6");
        labvel.add("7");
        labvel.add("8");
        labvel.add("9");
        labvel.add("10");
        labvel.add("11");
        labvel.add("12");
        labvel.add("");
        showBarChart();
        initBarChart();
        GroupBarChart(labvel,period);

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume");
        //getPrecParents();
        radioYear.performClick();

    }

    public void getThePeriod(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioYear:
                if(checked) {
                    period = 3;
                    getPrecParents();
                    getPrecParentsKid( );
                    getTotal();
                    getActivity();
                    ArrayList<String> labvel = new ArrayList<String>();
                    labvel.add("");
                    labvel.add("1");
                    labvel.add("2");
                    labvel.add("3");
                    labvel.add("4");
                    labvel.add("5");
                    labvel.add("6");
                    labvel.add("7");
                    labvel.add("8");
                    labvel.add("9");
                    labvel.add("10");
                    labvel.add("11");
                    labvel.add("12");
                    labvel.add("");
                    barchart = findViewById(R.id.barchart);
                    showBarChart();
                    initBarChart();
                    GroupBarChart(labvel,period);
                }
                break;
            case R.id.radioMonth:
                if(checked) {
                    period = 2;
                    getPrecParents();
                    getPrecParentsKid( );
                    getTotal();
                    getActivity();
                    ArrayList<String> labvel = new ArrayList<String>();
                    labvel.add("");
                    labvel.add("1");
                    labvel.add("2");
                    labvel.add("3");
                    labvel.add("4");
                    labvel.add("5");
                    labvel.add("");
                    barchart = findViewById(R.id.barchart);
                    showBarChart();
                    initBarChart();
                    GroupBarChart(labvel,period);
                }
                break;
            case R.id.radioWeek:
                if(checked) {
                    period = 1;
                    getPrecParents();
                    getPrecParentsKid( );
                    getTotal();
                    getActivity();
                    ArrayList<String> labvel = new ArrayList<String>();
                    labvel.add("");
                    labvel.add("1");
                    labvel.add("2");
                    labvel.add("3");
                    labvel.add("4");
                    labvel.add("5");
                    labvel.add("6");
                    labvel.add("7");
                    labvel.add("");
                    barchart = findViewById(R.id.barchart);
                    showBarChart();
                    initBarChart();
                    GroupBarChart(labvel,period);
                }
                break;
        }
    }

    private void addDataSetParents(ArrayList<PieEntry> yEntrys, ArrayList<String> xEntrys) {
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "             New Parents");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#ffbb33"));
        colors.add(Color.parseColor("#E8EAF6"));


        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieChart.setDrawSliceText(false);
        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieChart.invalidate();
        //pieChart.getLegend().setEnabled(false);


        ////////////////////////////////
    }

    private void addDataSetKids( ArrayList<PieEntry> yEntrys,ArrayList<String> xEntrys ) {


        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "                  New Children");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);



        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#4A92FC"));
        colors.add(Color.parseColor("#E8EAF6"));

        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieChartKids.setDrawSliceText((boolean) false);
        //add legend to chart
        Legend legend = pieChartKids.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChartKids.setData(pieData);

        pieChartKids.invalidate();
        //pieChart.getLegend().setEnabled(false);

        ////////////////////////////////

    }

    private void addDataSetTotal(){
        ArrayList<PieEntry> yEntrysTotal = new ArrayList<>();
        ArrayList<String> xEntrysTotal = new ArrayList<>();
        for(int i = 0; i < yDataTotal.length; i++){
            yEntrysTotal.add(new PieEntry(yDataTotal[i] , i));
        }

        for(int i = 1; i < xDataTotal.length; i++){
            xEntrysTotal.add(xDataTotal[i]);
        }
        PieDataSet pieDataSetTotal = new PieDataSet(yEntrysTotal, "     Total Per Category");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#E8EAF6"));
        colors.add(Color.parseColor("#4A92FC"));
        colors.add(Color.parseColor("#ffbb33"));
        colors.add(Color.parseColor("#4E7FE7"));


        pieDataSetTotal.setSliceSpace(2);
        pieDataSetTotal.setValueTextSize(12);
        pieDataSetTotal.setColors(colors);
        pieDataSetTotal.setDrawValues(true);
        pieDataSetTotal.setSliceSpace(0f);
        // pieChartTotal.setDrawSliceText(false);
        //add legend to chart


        pieDataSetTotal.setColors(colors);
        pieDataSetTotal.setDrawValues(true);
        pieDataSetTotal.setSliceSpace(0f);
        //  pieChartTotal.setDrawSliceText(true);
        ValueFormatter vf = new ValueFormatter() { //value format here, here is the overridden method
            @Override
            public String getFormattedValue(float value) {
                return ""+(int)value;
            }
        };
        pieDataSetTotal.setValueFormatter(vf);
        Legend legendTotal = pieChartTotal.getLegend();
        legendTotal.setForm(Legend.LegendForm.NONE);

        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legendTotal.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legendTotal.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legendTotal.setDrawInside(false);
        //create pie data object
        PieData pieDataTotal = new PieData(pieDataSetTotal);
        pieChartTotal.setData(pieDataTotal);

        pieChartTotal.invalidate();
    }

    private void addDataSetActivity(ArrayList<PieEntry> yEntrys,ArrayList<String> xEntrys ) {

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "          Monthly Activities In Hour");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#4E7FE7"));
        colors.add(Color.parseColor("#E8EAF6"));



        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
        pieDataSet.setSliceSpace(0f);
        pieChartActivity.setDrawSliceText(false);
        //add legend to chart
        Legend legend = pieChartActivity.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChartActivity.setData(pieData);
        pieChartActivity.invalidate();
        //pieChart.getLegend().setEnabled(false);

        ////////////////////////////////


    }

//        public void goToMain(View view) {
//        setContentView(R.layout.fragment_back);
//    }
    private void getPrecParents( ) {
        // display our progress bar.
System.out.println("fat awl mara");
        // create retrofit builder and pass our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8093")
                // when sending data in json format we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // and build our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);



        // passing data from our text fields to our modal class.

        // calling a method to create a post and passing our model class.
        retrofit2.Call<HashMap<String, Integer>> call = retrofitAPI.getParentsPe(period);

        // add the http request to queue
        call.enqueue(new Callback<HashMap<String, Integer>>() {
            @Override
            public void onResponse(retrofit2.Call<HashMap<String, Integer>> call, Response<HashMap<String, Integer>> response) {
                // this method is called when we get response from our api.
                int newParent=response.body().get("New Parents");
                int totalParent=response.body().get("totalParents");
                Integer[] time= {newParent,totalParent-newParent};
                String[] timeString={"activityTime","totalTime"};
                ArrayList<PieEntry> yEntrys = new ArrayList<>();
                ArrayList<String> xEntrys = new ArrayList<>();
                for (int i = 0; i < time.length; i++) {
                    yEntrys.add(new PieEntry(time[i].intValue(), i));
                }

                for (int i = 1; i < timeString.length; i++) {
                    xEntrys.add(timeString[i]);
                }
                pieChart = findViewById(R.id.activity_main_piechart);
//        setupPieChart();
                pieChart.setRotationEnabled(false);
                pieChart.setUsePercentValues(false);
                //pieChart.setHoleColor(Color.BLUE);
                //pieChart.setCenterTextColor(Color.BLACK);
                pieChart.setHoleRadius(83f);
                //pieChart.setCenterText("45");

                pieChart.setTransparentCircleAlpha(0);
                pieChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                pieChart.getDescription().setEnabled(false);
                pieChart.setHighlightPerTapEnabled(false);
                System.out.println("fat awl mara 2");
                pieChart.setCenterText(newParent+"");
                addDataSetParents(yEntrys,xEntrys);
            }

            @Override
            public void onFailure(retrofit2.Call<HashMap<String, Integer>> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.

            }

        });
    }

    private void adddata( ) {
        // display our progress bar.
        System.out.println("fat awl mara");
        // create retrofit builder and pass our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8093")
                // when sending data in json format we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // and build our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);



        // passing data from our text fields to our modal class.

        // calling a method to create a post and passing our model class.
        retrofit2.Call <Integer> call = retrofitAPI.getallData();

        // add the http request to queue
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(retrofit2.Call<Integer> call, Response<Integer> response) {
            }

            @Override
            public void onFailure(retrofit2.Call<Integer> call, Throwable t) {

            }

        });
    }

    private void getPrecParentsKid( ) {
        // display our progress bar.

        // create retrofit builder and pass our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8093")
                // when sending data in json format we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // and build our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);



        // passing data from our text fields to our modal class.

        // calling a method to create a post and passing our model class.
        retrofit2.Call<HashMap<String, Integer>> call = retrofitAPI.getNewKids(period);

        // add the http request to queue
        call.enqueue(new Callback<HashMap<String, Integer>>() {
            @Override
            public void onResponse(retrofit2.Call<HashMap<String, Integer>> call, Response<HashMap<String, Integer>> response) {
                // this method is called when we get response from our api.
                int newKids=response.body().get("newKids");
                int totalKids=response.body().get("totalKids");
                Integer[] time= {newKids,totalKids-newKids};
                String[] timeString={"activityTime","totalTime"};
                ArrayList<PieEntry> yEntrys = new ArrayList<>();
                ArrayList<String> xEntrys = new ArrayList<>();
                for (int i = 0; i < time.length; i++) {
                    yEntrys.add(new PieEntry(time[i].intValue(), i));
                }

                for (int i = 1; i < timeString.length; i++) {
                    xEntrys.add(timeString[i]);
                }

                pieChartKids = findViewById(R.id.activity_main_piechartKid);
                pieChartKids.setRotationEnabled(false);
                pieChartKids.setUsePercentValues(false);
                pieChartKids.setHoleRadius(83f);
                pieChartKids.setTransparentCircleAlpha(0);
                //pieChartKids.setCenterText("45");
                pieChartKids.setCenterTextSize(20);
                pieChartKids.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                pieChartKids.getDescription().setEnabled(false);
                pieChartKids.setHighlightPerTapEnabled(false);

                pieChartKids.setCenterText(newKids+"");
                addDataSetKids(yEntrys,xEntrys);
            }
            @Override
            public void onFailure(retrofit2.Call<HashMap<String, Integer>> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
               // pieChartKids.setCenterText("30");
            }

        });
    }

    private void getActivity( ) {
        // display our progress bar.

        // create retrofit builder and pass our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8093")
                // when sending data in json format we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // and build our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);



        // passing data from our text fields to our modal class.

        // calling a method to create a post and passing our model class.
        retrofit2.Call<HashMap<String, Double>> call = retrofitAPI.getActivityTime(period);

        // add the http request to queue
        call.enqueue(new Callback<HashMap<String, Double>>() {
            @Override
            public void onResponse(retrofit2.Call<HashMap<String, Double>> call, Response<HashMap<String, Double>> response) {
                // this method is called when we get response from our api.
                Double activityTime=response.body().get("activityTime");
                Double totalTime=response.body().get("totalTime");
                Double[] time={activityTime,totalTime};
                String[] timeString={"activityTime","totalTime"};
                ArrayList<PieEntry> yEntrys = new ArrayList<>();
                ArrayList<String> xEntrys = new ArrayList<>();
                for (int i = 0; i < time.length; i++) {
                    yEntrys.add(new PieEntry(time[i].intValue(), i));
                }

                for (int i = 1; i < timeString.length; i++) {
                    xEntrys.add(timeString[i]);
                }

                pieChartActivity = findViewById(R.id.activity_main_piechartActivity);
                pieChartActivity.setRotationEnabled(false);
                pieChartActivity.setUsePercentValues(false);
                pieChartActivity.setHoleRadius(83f);
                pieChartActivity.setTransparentCircleAlpha(0);
                pieChartActivity.setCenterText(activityTime+" ");
                pieChartActivity.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                pieChartActivity.setCenterTextSize(20);
                pieChartActivity.getDescription().setEnabled(false);
                pieChartActivity.setHighlightPerTapEnabled(false);


                addDataSetActivity(yEntrys,xEntrys);
            }
            @Override
            public void onFailure(retrofit2.Call<HashMap<String, Double>> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                // pieChartKids.setCenterText("30");
            }

        });
    }
    private void getTotal( ) {
        // display our progress bar.

        // create retrofit builder and pass our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8093")
                // when sending data in json format we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // and build our retrofit builder.
                .build();
        // create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);



        // passing data from our text fields to our modal class.

        // calling a method to create a post and passing our model class.
        retrofit2.Call<HashMap<String, Integer>> call = retrofitAPI.getTotal(period);

        // add the http request to queue
        call.enqueue(new Callback<HashMap<String, Integer>>() {
            @Override
            public void onResponse(retrofit2.Call<HashMap<String, Integer>> call, Response<HashMap<String, Integer>> response) {
                // this method is called when we get response from our api.
                int category1=response.body().get("category1");
                int category2 = response.body().get("category2");
                int category3=response.body().get("category3");
                int category4=response.body().get("category4");

                yDataTotal = new int[]{category1, category2, category3, category4};
//                pieChartTotal.setCenterText(newKids+"");
////////////////////////////////////////////////////////////////////////////////////////////
                ArrayList<PieEntry> yEntrysTotal = new ArrayList<>();
                ArrayList<String> xEntrysTotal = new ArrayList<>();
                for(int i = 0; i < yDataTotal.length; i++){
                    yEntrysTotal.add(new PieEntry(yDataTotal[i] , i));
                }

                for(int i = 1; i < xDataTotal.length; i++){
                    xEntrysTotal.add(xDataTotal[i]);
                }
                PieDataSet pieDataSetTotal = new PieDataSet(yEntrysTotal, "     Total Per Category");
                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.parseColor("#E8EAF6"));
                colors.add(Color.parseColor("#4A92FC"));
                colors.add(Color.parseColor("#ffbb33"));
                colors.add(Color.parseColor("#4E7FE7"));


                pieDataSetTotal.setSliceSpace(2);
                pieDataSetTotal.setValueTextSize(12);
                pieDataSetTotal.setColors(colors);
                pieDataSetTotal.setDrawValues(true);
                pieDataSetTotal.setSliceSpace(0f);
                // pieChartTotal.setDrawSliceText(false);
                //add legend to chart


                pieDataSetTotal.setColors(colors);
                pieDataSetTotal.setDrawValues(true);
                pieDataSetTotal.setSliceSpace(0f);
                //  pieChartTotal.setDrawSliceText(true);
                ValueFormatter vf = new ValueFormatter() { //value format here, here is the overridden method
                    @Override
                    public String getFormattedValue(float value) {
                        return ""+(int)value;
                    }
                };
                pieDataSetTotal.setValueFormatter(vf);
                Legend legendTotal = pieChartTotal.getLegend();
                legendTotal.setForm(Legend.LegendForm.NONE);

                // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
                legendTotal.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                legendTotal.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
                // legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                legendTotal.setDrawInside(false);
                //create pie data object
                PieData pieDataTotal = new PieData(pieDataSetTotal);
                pieChartTotal = findViewById(R.id.activity_main_piechartTotal);
                pieChartTotal.setRotationEnabled(false);
                pieChartTotal.setUsePercentValues(false);
                pieChartTotal.setHoleRadius(0f);
                pieChartTotal.setTransparentCircleAlpha(0);
                pieChartTotal.getDescription().setEnabled(false);
                pieChartTotal.setHighlightPerTapEnabled(false);
                pieChartTotal.setData(pieDataTotal);

                pieChartTotal.invalidate();
 ///////////////////////////////////////////////////////////////////////////////////////////
            }

            @Override
            public void onFailure(retrofit2.Call<HashMap<String, Integer>> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                pieChartKids.setCenterText("30");

            }

        });
    }

    private void showBarChart(){
        ArrayList<Double> valueList = new ArrayList<Double>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        //input data
        for(int i = 0; i < 6; i++){
            valueList.add(i * 100.1);
        }

        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i,valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(new int[] {Color.parseColor("#4A92FC"),
                Color.parseColor("#ffbb33"), Color.parseColor("#4E7FE7"), Color.GRAY, Color.BLUE});
        BarData data = new BarData(barDataSet);
        barchart.setData(data);
        barchart.invalidate();
        //Changing the color of the bar
        //barDataSet.setColor(Color.parseColor("#304567"));
        //Setting the size of the form in the legend
        barDataSet.setFormSize(15f);
        //showing the value of the bar, default true if not set
        barDataSet.setDrawValues(false);

        //setting the text size of the value of the bar
        barDataSet.setValueTextSize(12f);
    }
    private void initBarChart(){
        //hiding the grey background of the chart, default false if not set
        barchart.setDrawGridBackground(false);
        //remove the bar shadow, default false if not set
        barchart.setDrawBarShadow(false);
        //remove border of the chart, default false if not set
        barchart.setDrawBorders(false);

        //remove the description label text located at the lower right corner
        Description description = new Description();
        description.setEnabled(false);
        barchart.setDescription(description);

        //setting animation for y-axis, the bar will pop up from 0 to its value within the time we set
        barchart.animateY(1000);
        //setting animation for x-axis, the bar will pop up separately within the time we set
        barchart.animateX(1000);
        barchart.getDescription().setEnabled(false);
        barchart.setHighlightPerTapEnabled(false);
        XAxis xAxis = barchart.getXAxis();
        //change the position of x-axis to the bottom
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //set the horizontal distance of the grid line
        xAxis.setGranularity(1f);
        //hiding the x-axis line, default true if not set
        xAxis.setDrawAxisLine(false);
        //hiding the vertical grid lines, default true if not set
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = barchart.getAxisLeft();
        //hiding the left y-axis line, default true if not set
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = barchart.getAxisRight();
        //hiding the right y-axis line, default true if not set
        rightAxis.setDrawAxisLine(false);

        Legend legend = barchart.getLegend();
        //setting the shape of the legend form to line, default square shape
        legend.setForm(Legend.LegendForm.LINE);
        //setting the text size of the legend
        legend.setTextSize(11f);
        //setting the alignment of legend toward the chart
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        //setting the stacking direction of legend
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //setting the location of legend outside the chart, default false if not set
        legend.setDrawInside(false);
        xAxis.setDrawLabels(false);
        barchart.setHighlightPerTapEnabled(false);

    }
    private void GroupBarChart(ArrayList<String> labels,int period) {

        int numArray = 0;
        if(period == 1){
            numArray = 7;
        }
        else if(period == 2){
            numArray = 5;
        }
        else {
            numArray = 12;
        }
        //String[] labels = {"","1","2","3","4","5","6","7",""};
        barchart.setDrawBarShadow(false);
        barchart.getDescription().setEnabled(false);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(false);

        // empty labels so that the names are spread evenly
        XAxis xAxis = barchart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);


        YAxis leftAxis = barchart.getAxisLeft();
        leftAxis.setAxisMinimum(0);

        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(12);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularity(2);
        leftAxis.setLabelCount(numArray, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);


        barchart.getAxisRight().setEnabled(false);
        barchart.getLegend().setEnabled(false);

        float[] valOne ;
        float[] valTwo ;
        float[] valThree ;
        float[] valfour ;
        if(numArray==7) {
            valOne = new float[]{10, 12, 14, 13, 11, 12, 13};
             valTwo = new float[]{14, 18, 15, 12, 15, 18, 17};
             valThree = new float[]{14, 14, 17, 16, 19, 20, 10};
            valfour = new float[]{13, 17, 19, 16, 16, 14, 12};
        }
        else if(numArray==5) {
            valOne = new float[]{30, 28, 25, 23, 21};
            valTwo = new float[]{24, 22, 21, 23, 20};
             valThree =new float[] {34, 24, 30, 26, 29};
             valfour = new float[]{23, 27, 29, 26, 30};
        }
        else {
            valOne = new float[]{9, 8,11, 20, 12, 16, 14,6, 17,9, 13, 11};
           valTwo = new float[]{12, 13, 11, 10, 13, 18, 20,13, 12, 5, 13, 11};
           valThree = new float[]{14, 24, 18, 16, 19, 20, 15,16, 12, 5, 13, 11};
            valfour = new float[]{13, 17, 19, 16, 18, 14, 12,19, 12, 15, 13, 11};
        }

        ArrayList<BarEntry> barOne = new ArrayList<>();
        ArrayList<BarEntry> barTwo = new ArrayList<>();
        ArrayList<BarEntry> barThree = new ArrayList<>();
        ArrayList<BarEntry> barFour = new ArrayList<>();
        ArrayList<BarEntry> barFive = new ArrayList<>();
        ArrayList<BarEntry> barSix = new ArrayList<>();
        ArrayList<BarEntry> barSeven = new ArrayList<>();
        ArrayList<BarEntry> barEight = new ArrayList<>();
        ArrayList<BarEntry> barNine = new ArrayList<>();
        ArrayList<BarEntry> barTen = new ArrayList<>();
        ArrayList<BarEntry> barElevent = new ArrayList<>();
        ArrayList<BarEntry> barTwelve = new ArrayList<>();

        for (int i = 0; i < numArray; i++) {
            if(numArray >= 5) {
                barOne.add(new BarEntry(i, valOne[i]));
                barTwo.add(new BarEntry(i, valTwo[i]));
                barThree.add(new BarEntry(i, valThree[i]));
                barFour.add(new BarEntry(i, valfour[i]));
                barFive.add(new BarEntry(i, valfour[i]));
            }
            if(numArray >= 7) {
                barSix.add(new BarEntry(i, valfour[i]));
                barSeven.add(new BarEntry(i, valfour[i]));
            }
            if(numArray==12){
                barEight.add(new BarEntry(i, valfour[i]));
                barNine.add(new BarEntry(i, valfour[i]));
                barTen.add(new BarEntry(i, valfour[i]));
                barElevent.add(new BarEntry(i, valfour[i]));
                barTwelve.add(new BarEntry(i, valfour[i]));
            }
        }

        BarDataSet set1 = new BarDataSet(barOne, "Art");
        set1.setColor(Color.BLUE);
        BarDataSet set2 = new BarDataSet(barTwo, "Space");
        set2.setColor(Color.parseColor("#4E7FE7"));

        BarDataSet set3 = new BarDataSet(barThree, "Animal");
        set3.setColor(Color.GREEN);

        BarDataSet set4 = new BarDataSet(barFour, "Science");
        set4.setColor(Color.parseColor("#ffbb33"));


        BarData data = new BarData(set1, set2, set3, set4);
        barchart.setData(data);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));


        set1.setHighlightEnabled(false);
        set2.setHighlightEnabled(false);
        set3.setHighlightEnabled(false);
        set4.setHighlightEnabled(false);
        set1.setDrawValues(false);
        set2.setDrawValues(false);
        set3.setDrawValues(false);
        set4.setDrawValues(false);
        barchart.getAxisLeft().setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularityEnabled(true);

        float barSpace = 0.02f;
        float groupSpace = 0.3f;
        int groupCount = 5;

        data.setBarWidth(0.1f);

        barchart.getXAxis().setAxisMinimum(1);


        barchart.setVisibleXRangeMaximum(5f);
        // mChart.groupBars(1, 0.5f, 0f);

        barchart.groupBars(1, 0.6f, 0f);
        barchart.setVisibleXRangeMaximum(6f);

        xAxis.setAxisMaximum(15 - 1.1f);
        xAxis.setXOffset(6f);
        barchart.setData(data);
        barchart.setScaleEnabled(false);
        //mChart.setVisibleXRangeMaximum(6f);


        barchart.invalidate();
        barchart.setBackgroundColor(Color.WHITE);

        Legend l = barchart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        barchart.getLegend().setEnabled(true);
    }


}