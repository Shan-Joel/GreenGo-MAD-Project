package com.example.greengomadproject.ui;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;

import com.example.greengomadproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

public class AddItemInShopTable {

    public static void  addRow(final Context context, final TableLayout adddalTableLayout, final DatabaseReference databaseReference)
    {
        final TableRow tr=new TableRow(context);
        TableLayout.LayoutParams paramsForRow=new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        paramsForRow.setMargins(10,10,0,20);
        tr.setLayoutParams(paramsForRow);

        final TableRow.LayoutParams paramsForCategoryAndProduct=new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, .9f);
        final TableRow.LayoutParams paramsForQunatity=new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, .9f);
        final TableRow.LayoutParams paramsForUnit=new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.4f);

        final Spinner catSpinner = new Spinner(context);
        catSpinner.setLayoutParams(paramsForCategoryAndProduct);

        final Spinner productSpinner = new Spinner(context);
        catSpinner.setLayoutParams(paramsForCategoryAndProduct);

        final EditText quantity= new EditText(context);
        quantity.setLayoutParams(paramsForQunatity);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER);

        final Spinner unitSpinner = new Spinner(context);
        catSpinner.setLayoutParams(paramsForUnit);

        final EditText etNewCategory= new EditText(context);
        etNewCategory.setLayoutParams(paramsForCategoryAndProduct);
        etNewCategory.setHint("New Category");

        final EditText etNewProduct= new EditText(context);
        etNewCategory.setLayoutParams(paramsForCategoryAndProduct);
        etNewCategory.setHint("New Product");

        final TableRow.LayoutParams rowParamsMinus=new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, .5f);
        ImageButton minusButton=new ImageButton(context);
        minusButton.setImageResource(R.drawable.ic_baseline_indeterminate_check_box_24);
        minusButton.setLayoutParams(rowParamsMinus);

        fetchUnitInSpinner(databaseReference,context,unitSpinner);
        fetchCategoryandProduct(databaseReference,context,catSpinner,productSpinner,tr,etNewCategory,etNewProduct);
        addorRemoveProductSpinner(productSpinner,tr,etNewProduct);
    }

    private static void fetchCategoryandProduct(DatabaseReference databaseReference, final Context context, final Spinner catSpinner, final Spinner productSpinner, final TableRow tr, final EditText etNewCategory, final EditText etNewProduct)


    private static void fetchCategoriesInSpinner(DatabaseReference databaseReference, final Context context, final Spinner catSpinner)
    {
        databaseReference.child("categories").addChildEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final ArrayList<String> catList = new ArrayList<>();
                for (DataSnapshot areaSnapShot : dataSnapshot.getChildern())
                {
                    catList.add(areaSnapShot.getKey());
                }
                Collection.sort(catList);
                catList.add("Others");

                final ArrayAdapter<String> catAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, catList);
                catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                catSpinner.setAdapter(catAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static void fetchUnitInSpinner(DatabaseReference databaseReference, final Context context, final Spinner unitSpinner) {

        databaseReference.child("unit").addChildEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final ArrayList<String> unitList = new ArrayList<>();
                for (DataSnapshot areaSnapShot : dataSnapshot.getChildern()) {
                    unitList.add(areaSnapShot.getValue(String.class));
                }
                Collection.sort(unitList);

                final ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, unitList);
                unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitSpinner.setAdapter(unitAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
