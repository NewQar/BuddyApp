package com.example.csc557buddyapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;

import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment implements View.OnClickListener {
    DatabaseHandler db;
    EditText eId, enickname, efullname, ephonenum, eemail, dobEditText;
    RadioGroup genderGroup;  // Add RadioGroup
    Button bAdd, bView, bUpdate, bDelete, bSearch, pickDateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        eId = view.findViewById(R.id.id);
        enickname = view.findViewById(R.id.nickname);
        efullname = view.findViewById(R.id.fullname);
        ephonenum = view.findViewById(R.id.phonenum);
        eemail = view.findViewById(R.id.email);
        dobEditText = view.findViewById(R.id.dobEditText);

        genderGroup = view.findViewById(R.id.genderGroup);  // Initialize RadioGroup

        bAdd = view.findViewById(R.id.add);
        bView = view.findViewById(R.id.view);
        bUpdate = view.findViewById(R.id.update);
        bDelete = view.findViewById(R.id.delete);
        bSearch = view.findViewById(R.id.search);
        pickDateButton = view.findViewById(R.id.btnDatePicker);

        bAdd.setOnClickListener(this);
        bView.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bSearch.setOnClickListener(this);
        pickDateButton.setOnClickListener(this);

        db = new DatabaseHandler(requireContext());

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            addFriend();
        } else if (v.getId() == R.id.view) {
            viewFriends();
        } else if (v.getId() == R.id.update) {
            updateFriend();
        } else if (v.getId() == R.id.delete) {
            deleteFriend();
        } else if (v.getId() == R.id.search) {
            searchFriend();
        } else if (v.getId() == R.id.btnDatePicker) {  // Handle click for pickDateButton
            showDatePicker();
        }
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the Date of Birth EditText with the selected date
                        dobEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void addFriend() {
        String nickname = enickname.getText().toString();
        String fullname = efullname.getText().toString();
        String phonenum = ephonenum.getText().toString();
        String email = eemail.getText().toString();
        String dob = dobEditText.getText().toString();

        // Get selected gender
        RadioButton selectedGenderRadioButton = getView().findViewById(genderGroup.getCheckedRadioButtonId());
        String gender = selectedGenderRadioButton != null ? selectedGenderRadioButton.getText().toString() : "";

        if (nickname.equals("") || fullname.equals("") || phonenum.equals("") || email.equals("") || gender.equals("") || dob.equals("")) {
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            // Pass the DOB parameter to addFriend method
            long result = db.addFriend(nickname, fullname, phonenum, email, gender, dob);
            if (result != -1) {
                Toast.makeText(requireContext(), "Friend Added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Failed to add friend", Toast.LENGTH_SHORT).show();
            }
            clearFields();
        }
    }

    private void viewFriends() {
        String data = db.getFriend();
        if (data.equals("")) {
            Toast.makeText(requireContext(), "No friends found...", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(requireContext(), SecondActivity.class);
            startActivity(intent);
        }
    }

    private void updateFriend() {
        String id = eId.getText().toString().trim();
        String nickname = enickname.getText().toString().trim();
        String fullname = efullname.getText().toString();
        String phonenum = ephonenum.getText().toString();
        String email = eemail.getText().toString();

        if (id.equals("") || nickname.equals("") || fullname.equals("") || phonenum.equals("") || email.equals("")) {
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            long friendId = Long.parseLong(id);
            db.updateFriend(friendId, nickname, fullname, phonenum, email);
            Toast.makeText(requireContext(), "Friend Info Updated!", Toast.LENGTH_SHORT).show();
            clearFields();
        }
    }

    private void deleteFriend() {
        String id = eId.getText().toString();

        if (id.equals("")) {
            Toast.makeText(requireContext(), "Please Fill the ID", Toast.LENGTH_SHORT).show();
        } else {
            long friendId = Long.parseLong(id);
            db.deleteFriend(friendId);
            Toast.makeText(requireContext(), "Friend Deleted...", Toast.LENGTH_SHORT).show();
            clearFields();
        }
    }

    private void searchFriend() {
        String id, nickname, fullname, phonenum, email;

        id = eId.getText().toString().trim();

        if (id.equals("")) {
            Toast.makeText(requireContext(), "Please fill the ID", Toast.LENGTH_SHORT).show();
        } else {
            try {
                long l1 = Long.parseLong(id);
                nickname = db.getNickname(l1);
                fullname = db.getFullname(l1);
                phonenum = db.getPhonenum(l1);
                email = db.getEmail(l1);

                enickname.setText(nickname);
                efullname.setText(fullname);
                ephonenum.setText(phonenum);
                eemail.setText(email);

                Toast.makeText(requireContext(), "Friend Found!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(requireContext(), "ID is not valid", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Helper method to clear input fields
    private void clearFields() {
        eId.setText("");
        enickname.setText("");
        efullname.setText("");
        ephonenum.setText("");
        eemail.setText("");
        genderGroup.clearCheck();
        dobEditText.setText("");// Clear the selected radio button
    }
}
