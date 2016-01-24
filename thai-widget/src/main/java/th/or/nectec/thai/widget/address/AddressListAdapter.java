/*
 * Copyright © 2015 NECTEC
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
 */

package th.or.nectec.thai.widget.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import th.or.nectec.thai.address.AddressEntity;
import th.or.nectec.thai.widget.R;

import java.util.ArrayList;
import java.util.List;

class AddressListAdapter<T extends AddressEntity> extends BaseAdapter {

    List<T> addressList;
    LayoutInflater mInflater;

    public AddressListAdapter(Context context, List<T> addressList) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addAll(addressList);
    }


    public void addAll(List<T> collection) {
        addressList = new ArrayList<>();
        addressList.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public T getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_address, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        AddressEntity entity = getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.name.setText(entity.getName());
        viewHolder.code.setText("#" + entity.getCode());
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView code;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.code = (TextView) view.findViewById(R.id.code);
        }
    }
}
