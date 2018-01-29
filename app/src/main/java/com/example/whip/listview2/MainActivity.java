package com.example.whip.listview2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.whip.listview2.toutv.Lineups;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MyAdapter adapter;
    ListView list;
    Lineups films;
    TextView tv;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview1);
        RunAPI run = new RunAPI();
        run.execute();
    }

    public class RunAPI extends AsyncTask<String, Object, Lineups>{

        @Override
        protected Lineups doInBackground(String... strings) {

            WebAPI web = new WebAPI();
            try{
                films = web.run();
            }catch (IOException e){
                e.printStackTrace();
            }
            return films;
        }

        @Override
        protected void onPostExecute(Lineups lineups) {
            super.onPostExecute(lineups);
            adapter = new MyAdapter();
            list.setAdapter(adapter);

        }
    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public MyAdapter(){
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return films.LineupItems.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view==null){
                view = inflater.inflate(R.layout.rangee,viewGroup,false);
            }

            tv = (TextView) view.findViewById(R.id.textview1);
            im = (ImageView) view.findViewById(R.id.imgview1);

            String title = films.LineupItems.get(i).GenreTitle;
            tv.setText(title);

            String url = films.LineupItems.get(i).ImagePlayerNormalC;

            Picasso.with(getApplicationContext()).load(url).into(im);
            return view;
        }
    }
}
