import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StrokeMap;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StrokeChooserPanel;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

import java.awt.*;

public class Spiral extends ApplicationFrame {

    public Spiral(String title,int size) {
        super(title);
        JFreeChart xy = ChartFactory.createXYLineChart(
                title+" , of size ="+size ,
                "X" ,
                "Y" ,
                createDataset(size) ,
                PlotOrientation.VERTICAL ,
                false , false , false);

        ChartPanel chartPanel = new ChartPanel(xy);
        chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
        final XYPlot plot = xy.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.BLACK );
        renderer.setSeriesStroke( 0, new BasicStroke(0.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset(int n) {
        final XYSeries spiral = new XYSeries( "spiral" );


        for(int i=0; i<2*n-1; i++)
        {
            double a = i%2==0?0.5:0, b=0, r=(i+1)/2.0;
            double normal_x,y;
            int f = 10;
            for(int x=(int)(a-r)*f; x<=(a+r)*f; x++)
            {
                normal_x =(double)x/f;
                y = sqrt(pow(r,2)-pow(normal_x-a,2))+b;
                spiral.add(normal_x,i%2==0?y:(-1)*y);
            }
        }

        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( spiral );

        return dataset;
    }

    public static void main(String[] args) {

        int size = 15; // get from user after checking input, for later !!! //


        Spiral chart = new Spiral("Spiral",size);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
