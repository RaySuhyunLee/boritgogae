package net.raysuhyunlee.boritgogae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by SuhyunLee on 2015. 12. 11..
 */
public class InjectionArrayAdapter<T> extends ArrayAdapter<T> {
    private List<T> list;
    private final int resourceId;
    private LayoutInflater inflater;
    private final GetViewInterface getViewInterface;

    public InjectionArrayAdapter(Context context, int resourceId,
                                 List<T> list, GetViewInterface getViewInterface) {
        super(context, resourceId, list);
        this.list = list;
        this.resourceId = resourceId;
        this.getViewInterface = getViewInterface;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }
        return getViewInterface.getView(list.get(position), position, view);
    }

    public interface GetViewInterface {
        View getView(Object object, int position, View view);   // FIXME object -> T? But it's static
    }
}
