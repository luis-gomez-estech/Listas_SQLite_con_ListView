package com.luisgomez.listas_sqlite_forma2;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SitiosAdapter extends BaseAdapter {

    ArrayList<Sitio> arrayList;
    LayoutInflater inflater;
    ViewHolder holder = null;

    public SitiosAdapter(Context context, ArrayList<Sitio> arrayList) {
        this.arrayList = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int pos) {
        return arrayList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup root) {

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_sitio, root,
                    false);
            holder = new ViewHolder();

            holder.text = (TextView) view.findViewById(R.id.todos_los_datos);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Guardo los campos recibiendolos del arraylist
        String nombreSitio = arrayList.get(pos).getNombreSitio();
        String nombreCiudad = arrayList.get(pos).getNombreCiudad();
        String nombrePais = arrayList.get(pos).getNombrePais();

        // Los datos que se muestran en la lista a traves del ViewHolder
        holder.text.setText("Sitio : " + nombreSitio + "\n" + "Ciudad : " + nombreCiudad + "\n"
                + "Pais : " + nombrePais);

        return view;
    }

    public class ViewHolder {

        TextView text;

    }

}
