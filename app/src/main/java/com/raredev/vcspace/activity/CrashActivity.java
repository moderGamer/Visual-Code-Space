package com.raredev.vcspace.activity;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;

import androidx.viewbinding.BuildConfig;
import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.snackbar.Snackbar;
import com.raredev.vcspace.R;
import com.raredev.vcspace.databinding.ActivityCrashBinding;
import com.raredev.vcspace.databinding.ActivityMainBinding;

public class CrashActivity extends VCSpaceActivity {

  private ActivityCrashBinding binding;

  @Override
  public void findBinding() {
    binding = ActivityCrashBinding.inflate(getLayoutInflater());
  }

  @Override
  public View getLayout() {
    return binding.getRoot();
  }

  @Override
  public void onCreate() {
    binding = ActivityCrashBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());
    setSupportActionBar(binding.topAppBar);

    var error = new StringBuilder();

    error.append("Manufacturer: " + DeviceUtils.getManufacturer() + "\n");
    error.append("Device: " + DeviceUtils.getModel() + "\n");
    error.append(getIntent().getStringExtra("Software"));
    error.append("\n\n");
    error.append(getIntent().getStringExtra("Error"));
    error.append("\n\n");
    error.append(getIntent().getStringExtra("Date"));

    binding.result.setText(error.toString());

    binding.fab.setOnClickListener(
        v -> {
          ClipboardUtils.copyText(binding.result.getText());
          ToastUtils.showShort("Copied");
        });
  }

  @Override
  public void onBackPressed() {
    finishAffinity();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    var close = menu.add(getString(R.string.close));
    close.setContentDescription("Close App");

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getTitle().equals(getString(R.string.close))) {
      finishAffinity();
      return true;
    }
    return false;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    binding = null;
  }
}
