/*
 * Copyright (c) 2016 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package nectec.thai.widget.address;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import nectec.thai.address.AddressEntity;
import th.or.nectec.thai.widget.BuildConfig;
import th.or.nectec.thai.widget.R;

class AddressListAdapter<T extends AddressEntity> extends BaseAdapter implements Filterable {

    private final LayoutInflater mInflater;
    private final Context context;
    private List<T> addressList;
    private List<T> filteredList;

    public AddressListAdapter(Context context, List<T> addressList) {
        super();
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addAll(addressList);
    }

    public final void addAll(List<T> collection) {
        addressList = new ArrayList<>(collection);
        filteredList = new ArrayList<>(collection);
        notifyDataSetChanged();
    }

    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_address, parent, false);
            convertView.setTag(new ViewHolder(convertView));
            convertView.setClickable(false);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.setValue(getItem(position));

        return convertView;
    }

    @Override public T getItem(int position) {
        return filteredList.get(position);
    }

    @Override public int getCount() {
        return filteredList.size();
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public Filter getFilter() {
        return new Filter() {
            @Override protected FilterResults performFiltering(CharSequence constraint) {
                if (TextUtils.isEmpty(constraint)) {
                    return null;
                }

                FilterResults result = new FilterResults();
                ArrayList<T> ts = new ArrayList<>();
                for (T t : addressList) {
                    if (t.getName().contains(constraint))
                        ts.add(t);
                }
                result.count = ts.size();
                result.values = ts;
                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null) {
                    filteredList = (List<T>) results.values;
                } else {
                    filteredList = new ArrayList<>(addressList);
                }
                notifyDataSetChanged();
            }
        };
    }

    private class ViewHolder {
        protected TextView name;
        protected TextView code;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.code = (TextView) view.findViewById(R.id.code);
            code.setVisibility(BuildConfig.DEBUG ? View.VISIBLE : View.GONE);
        }

        void setValue(T entity) {
            name.setText(entity.getName());
            code.setText(context.getString(R.string.address_code_format, entity.getCode()));
        }
    }
}
