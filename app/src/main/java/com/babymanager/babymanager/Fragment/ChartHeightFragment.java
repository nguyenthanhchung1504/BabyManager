package com.babymanager.babymanager.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.babymanager.babymanager.Database.BabyMangerDatabase;
import com.babymanager.babymanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartHeightFragment extends Fragment {

    private static ChartHeightFragment INSTANCE = null;
    @BindView(R.id.any_chart_view)
    AnyChartView anyChartView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    Unbinder unbinder;

    public static ChartHeightFragment getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ChartHeightFragment();
        return INSTANCE;
    }

    public ChartHeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart_height, container, false);
        unbinder = ButterKnife.bind(this, view);
        anyChartView.setProgressBar(progressBar);
        Cartesian cartesian = AnyChart.column();
        BabyMangerDatabase database = new BabyMangerDatabase(getContext());

        float jan_height = database.getAvgHeight(1);
        float fer_height = database.getAvgHeight(2);
        float mar_height = database.getAvgHeight(3);
        float apr_height = database.getAvgHeight(4);
        float may_height = database.getAvgHeight(5);
        float jun_height = database.getAvgHeight(6);
        float jul_height = database.getAvgHeight(7);
        float aug_height = database.getAvgHeight(8);
        float sep_height = database.getAvgHeight(9);
        float oct_height = database.getAvgHeight(10);
        float nov_height = database.getAvgHeight(11);
        float dec_height = database.getAvgHeight(12);

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry(getString(R.string.jan), jan_height));
        data.add(new ValueDataEntry(getString(R.string.fer), fer_height));
        data.add(new ValueDataEntry(getString(R.string.mar), mar_height));
        data.add(new ValueDataEntry(getString(R.string.apr), apr_height));
        data.add(new ValueDataEntry(getString(R.string.may), may_height));
        data.add(new ValueDataEntry(getString(R.string.jun), jun_height));
        data.add(new ValueDataEntry(getString(R.string.jul), jul_height));
        data.add(new ValueDataEntry(getString(R.string.aug), aug_height));
        data.add(new ValueDataEntry(getString(R.string.sep), sep_height));
        data.add(new ValueDataEntry(getString(R.string.oct), oct_height));
        data.add(new ValueDataEntry(getString(R.string.nov), nov_height));
        data.add(new ValueDataEntry(getString(R.string.dec), dec_height));
        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }"+" cm");

        cartesian.animation(true);

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title(getString(R.string.month));
        cartesian.yAxis(0).title(getString(R.string.height));

        anyChartView.setChart(cartesian);
        return view;

    }

}
