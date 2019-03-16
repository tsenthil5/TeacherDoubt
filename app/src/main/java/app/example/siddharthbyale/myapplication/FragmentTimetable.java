package app.example.siddharthbyale.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentTimetable extends Fragment {
    ListView senthilsListView;
    ArrayAdapter<String> Daysadapter;
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_timetable,container,false);
        senthilsListView =  (ListView) rootview.findViewById(R.id.senthilsListView);

        Daysadapter = new ArrayAdapter<String>( rootview.getContext(), android.R.layout.simple_list_item_1, days);
        senthilsListView.setAdapter(Daysadapter);

        senthilsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(rootview.getContext(),"Pos: "+i,Toast.LENGTH_SHORT).show();
                switch (i) {
                    case 0: startActivity(new Intent(getActivity(),MainActivity1.class).putExtra("day","Monday"));
                            break;
                    case 1: startActivity(new Intent(getActivity(),MainActivity1.class).putExtra("day","Tuesday"));
                        break;
                    case 2: startActivity(new Intent(getActivity(),MainActivity1.class).putExtra("day","Wednesday"));
                        break;
                    case 3: startActivity(new Intent(getActivity(),MainActivity1.class).putExtra("day","Thursday"));
                        break;
                    case 4: startActivity(new Intent(getActivity(),MainActivity1.class).putExtra("day","Friday"));
                        break;
                }
            }
        });

 return rootview;
    }
}
