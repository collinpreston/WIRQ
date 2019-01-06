package org.wirq.collinhpreston.wirq;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by collinhpreston on 07/03/2017.
 */

public class ConnectLinksFragment extends Fragment{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.connect_links_fragment, container, false);
        Button facebookBtn = view.findViewById(R.id.facebookBtn);
        Button twitterBtn = view.findViewById(R.id.twitterBtn);
        Button websiteBtn = view.findViewById(R.id.websiteBtn);
        Button emailBtn = view.findViewById(R.id.emailBtn);

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookIntent();
                //newFacebookIntent(getContext().getPackageManager(), "https://www.facebook.com/WIRQ-210216633926/");
            }
        });
        twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwitterIntent();
            }
        });
        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWebsiteIntent();
            }
        });
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEmailIntent();
            }
        });

        return view;
    }

    private void newEmailIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"wirqfm@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "SUBSCRIBE");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    private void newWebsiteIntent() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://westirondequoiths.ss8.sharpschool.com/activities__clubs/wirq_90_9fm")));
    }
    private void newTwitterIntent() {
        Uri uri = Uri.parse("http://twitter.com/WIRQOfficial");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.twitter.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://twitter.com/WIRQOfficial")));
        }
    }
    private void facebookIntent() {
        Uri uri = Uri.parse("https://www.facebook.com/WIRQ-210216633926/");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.facebook.katana");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/WIRQ-210216633926/")));
        }
    }
}
