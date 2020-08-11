package tdc.edu.vn.qlsv.GiaoDien;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.qlsv.Adapter.CustomAdapterTB;
import tdc.edu.vn.qlsv.Database.DataThietBi;
import tdc.edu.vn.qlsv.Model.ThietBi;
import tdc.edu.vn.qlsv.R;

public class ActivityDetail extends AppCompatActivity {
    RecyclerView recyclerView;
    DataThietBi databaseThietBi;
    CustomAdapterTB adapterTB;
    ArrayList<ThietBi> dataThietBi;
    int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setControl();
        setEvent();
    }

    private void setEvent() {
        adapterTB=new CustomAdapterTB(R.layout.list_custom_thietbi,dataThietBi);
        recyclerView.setAdapter(adapterTB);
        SensorProximity();
    }

    private void setControl() {
        recyclerView=findViewById(R.id.recyclerViewChiTiet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseThietBi=new DataThietBi(this);
        dataThietBi=new ArrayList<>();
    }

    private void getOnceDevice(int position){
        if(position+1 > databaseThietBi.getAllThietBi().size()) {
            Toast.makeText(this, "Da het thiet bi de xem !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            dataThietBi.clear();
            dataThietBi.add(databaseThietBi.getAllThietBi().get(position));
        }
    }
    private void SensorProximity(){
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        final Sensor proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getOnceDevice(position++);
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    adapterTB.notifyDataSetChanged();
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener,proximitySensor,2*1000*1000);
    }
}