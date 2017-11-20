package com.example.katsigianni.pocketlocation;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;

public class HomeActivity extends AppCompatActivity implements OnItemSelectedListener {
    Spinner s1,s2;
    EditText Name, Surname, Personal_number, Blood, Oxygen;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Name = (EditText) findViewById(R.id.name);
        Surname = (EditText) findViewById(R.id.surname);
        Personal_number = (EditText) findViewById(R.id.personal_number);
        Blood = (EditText) findViewById(R.id.blood);
        Oxygen = (EditText) findViewById(R.id.oxygen);
        s1 = (Spinner)findViewById(R.id.spinner1);
        s2 = (Spinner)findViewById(R.id.spinner2);
        s1.setOnItemSelectedListener(this);

     /*   //get the spinner from the xml.
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Blood diseases", "Endocrine and metabolic diseases", "Psychic disorders", "Opthalmological diseases", "Ear disorders", "Circulatory System disorders", "Respiratory System disorders", "Digestive System disorders", "Skin diseases", "Musculoskeletal System diseases", "Urogenital System diseases", "External causes of morbidity and mortality"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter); */

    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp1= String.valueOf(s1.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        if(sp1.contentEquals("Blood diseases")) {
            List<String> list = new ArrayList<String>();
            list.add("Nutritional Anemias");
            list.add("Haemolytic Anemias");


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(sp1.contentEquals("Endocrine and metabolic diseases")) {
            List<String> list = new ArrayList<String>();
            list.add("Blood Clotting Disorders");
            list.add("Thyroid gland disorders");
            list.add("Disorders of other endocrine glands");
            list.add("Malnutrition");
            list.add("Obesity and other situations of overpowering");
            list.add("Metabolic Disorders");
            list.add("Diabetes");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Psychic disorders")) {
            List<String> list = new ArrayList<String>();
            list.add("Schizophrenia and Delusional Disorders");
            list.add("Organic Mental Disorders");
            list.add("Mood Disorders");
            list.add("Mental Disturbances due to use Psychoactive Substances");
            list.add("Mental Dysfunction");
            list.add("Personal and Behavioral Disorders");
            list.add("Neuronal,Stress-related and Bodily-like Disorders");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Opthalmological diseases")) {
            List<String> list = new ArrayList<String>();
            list.add("Disturbances of the hard,corneal,iris and radial body");
            list.add("Lens Disorders");
            list.add("Eyelid, lacrimal and ocular disorders");
            list.add("Choroid and retinal disorders");
            list.add("Glaukoma");
            list.add("Disorders of the vitreous body and eyeball");
            list.add("Optic nerve and visual disturbances");
            list.add("Visual disturbances and blindness");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }


        if(sp1.contentEquals("Ear disorders")) {
            List<String> list = new ArrayList<String>();
            list.add("Diseases of the outside ear");
            list.add("Diseases of middle ear and mastoid apoptosis");
            list.add("Diseases of the inner ear");
            list.add("Other ear disorders");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Circulatory System disorders")) {
            List<String> list = new ArrayList<String>();
            list.add("Αcute rheumatic fever");
            list.add("Chronic rheumatic heart disease");
            list.add("Hypertensive diseases");
            list.add("Ischemic heart diseases");
            list.add("Pulmonary heart disease and pulmonary circulation diseases");
            list.add("Other forms of heart disease");
            list.add("Cerebrovascular diseases");
            list.add("Diseases of arteries, arterioles and capillaries");
            list.add("Diseases of veins, lymph nodes and lymph nodes");
            list.add("Other and undefined disorders of the circulatory system");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }


        if(sp1.contentEquals("Respiratory System disorders")) {
            List<String> list = new ArrayList<String>();
            list.add("Acute infections of the upper respiratory tract");
            list.add("Influenza and pneumonia");
            list.add("Other acute infections of the lower respiratory tract");
            list.add("Other diseases of the upper respiratory tract");
            list.add("Chronic diseases of the lower respiratory tract");
            list.add("Pulmonary diseases due to external factors");
            list.add("Other respiratory diseases primarily affecting the interstitial tissue");
            list.add("Diabetic and necrotic conditions of the lower respiratory tract");
            list.add("Other diseases of the pleura");
            list.add("Other disorders of the respiratory system");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Digestive System disorders")) {
            List<String> list = new ArrayList<String>();
            list.add("Diseases of the oral cavity, salivary glands and jaws");
            list.add("Diseases of the esophagus, stomach and duodenum");
            list.add("Diseases of the appendix");
            list.add("Hernia");
            list.add("Non infectious enteritis and colitis");
            list.add("Other bowel diseases");
            list.add("Diseases of the peritoneum");
            list.add("Diseases of the liver");
            list.add("Gallbladder, biliary tract and pancreas disorders");
            list.add("Other diseases of the digestive system");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Skin diseases")) {
            List<String> list = new ArrayList<String>();
            list.add("Infections of the skin and subcutaneous tissue");
            list.add("Bleeding dermatoses");
            list.add("Dermatitis and eczema");
            list.add("Swallowtail skin dermatoses");
            list.add("Hives and erythema");
            list.add("Skin and subcutaneous tissue disorders associated with radiation");
            list.add("Disturbances of skin components");
            list.add("Other disorders of the skin and subcutaneous tissue");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }


        if(sp1.contentEquals("Musculoskeletal System diseases")) {
            List<String> list = new ArrayList<String>();
            list.add("Arthritis");
            list.add("Systemic diseases of the connective tissue");
            list.add("Soft tissue disorders");
            list.add("Osteoarthritis and cartilage diseases");
            list.add("Other diseases of the musculoskeletal system and connective tissue");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

        if(sp1.contentEquals("Urogenital System diseases")) {
            List<String> list = new ArrayList<String>();
            list.add("Diseases of the renal glomerus");
            list.add("Transmembrane nephropathies");
            list.add("Renal failure");
            list.add("Urolithiasis");
            list.add("Other disorders of the kidney and ureter");
            list.add("Other diseases of the urinary tract");
            list.add("Diseases of male genitalia");
            list.add("Diseases of the breast");
            list.add("Inflammatory diseases of the organs of the female pelvis");
            list.add("Non-inflammatory diseases of female genital organs");
            list.add("Other disorders of the urogenital system");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }


        if(sp1.contentEquals("External causes of morbidity and mortality")) {
            List<String> list = new ArrayList<String>();
            list.add("Head injuries");
            list.add("Cervical injuries");
            list.add("Chest injuries");
            list.add("Injuries of the abdomen,lower back,lumbar spine,spine and pelvis");
            list.add("Shoulder and arm injuries");
            list.add("Elbow and forearm injuries");
            list.add("Injuries of the wrist and limb");
            list.add("Knee and tibial injuries");
            list.add("Injury of the ankle and foot limb");
            list.add("Injuries involving multiple areas of the body");
            list.add("Injuries to an undetermined portion of the trunk,limb or area of the body");
            list.add("Heat and chemical burns");
            list.add("Frostbite");
            list.add("Poisoning by drugs and biological substances");
            list.add("Toxic effects from substances of primarily non-pharmaceutical origin");
            list.add("Complications of surgical and medical care");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

        // TODO Auto-generated method stub
    }


    public void adduser (View view){
        String name = Name.getText().toString();
        String surname = Surname.getText().toString();
        String personal_number = Personal_number.getText().toString();
        String blood = Blood.getText().toString();
        String oxygen = Oxygen.getText().toString();
        userDbHelper = new UserDbHelper(context);
        sqLiteDatabase = userDbHelper.getWritableDatabase();
        userDbHelper.addInformation(name,surname,personal_number,blood,oxygen,sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Account Successfully Created",Toast.LENGTH_LONG).show();
        Intent signinpage = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(signinpage);
        userDbHelper.close();
    }

}
