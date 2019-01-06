package org.wirq.collinhpreston.wirq;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Created by collinhpreston on 07/03/2017.
 */

public class RequestSongFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.request_song_fragment, container, false);
        Button sendBtn = view.findViewById(R.id.btnSendRequest);
        LinearLayout btnGroupLayout = view.findViewById(R.id.buttonLayout2);
        final LinearLayout contentContainer = view.findViewById(R.id.contentContainer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnGroupLayout.setElevation(18);
        }
        final EditText nameTxtBox = view.findViewById(R.id.editTxtName);
        final CheckBox includeNameChkBox = view.findViewById(R.id.chkBoxName);
        final EditText requestSongTxtBox = view.findViewById(R.id.editTxtSong);
        final EditText requestBandTxtBox = view.findViewById(R.id.editTxtBand);

        ViewGroup.LayoutParams contentLayoutParams = contentContainer.getLayoutParams();
        contentContainer.setLayoutParams(contentLayoutParams);

        includeNameChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if (isChecked) {
                         nameTxtBox.setVisibility(View.VISIBLE);
                         //ViewGroup.LayoutParams contentLayoutParams = contentContainer.getLayoutParams();
                         //contentLayoutParams.height = contentLayoutParams.height + 100;
                         //contentContainer.setLayoutParams(contentLayoutParams);
                     } else {
                         nameTxtBox.setVisibility(View.INVISIBLE);
                         //ViewGroup.LayoutParams contentLayoutParams = contentContainer.getLayoutParams();
                         //contentLayoutParams.height = contentLayoutParams.height - 100;
                         //contentContainer.setLayoutParams(contentLayoutParams);
                     }
                 }
             }
        );
        nameTxtBox.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTxtBox.setText("");
            }
        });


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                final PackageManager packageManager = getContext().getPackageManager();
                List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);

                if(list.size() == 0) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"wirqrequests@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "SONG REQUEST");
                if (includeNameChkBox.isChecked()) {
                    String songRequestAndName = requestSongTxtBox.getText() + " by " + requestBandTxtBox.getText() + " from listener: " + nameTxtBox.getText();
                    intent.putExtra(Intent.EXTRA_TEXT, songRequestAndName);
                }
                else {
                    String songRequest = requestSongTxtBox.getText() + " by " + requestBandTxtBox.getText();
                    intent.putExtra(Intent.EXTRA_TEXT, songRequest);
                }
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}
