//package com.liangfengyouxin.www.android.normal.publish.scan;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.res.AssetFileDescriptor;
//import android.graphics.Bitmap;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.media.MediaPlayer.OnCompletionListener;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Vibrator;
//import android.support.annotation.NonNull;
//import android.support.v4.content.ContextCompat;
//import android.view.SurfaceHolder;
//import android.view.SurfaceHolder.Callback;
//import android.view.SurfaceView;
//import android.widget.Toast;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.Result;
//import com.liangfengyouxin.www.android.frame.base.BaseActivity;
//import com.redstar.mainapp.R;
//import com.redstar.mainapp.frame.base.HxBaseActivity;
//import com.redstar.mainapp.frame.block.scanblock.zxing.camera.CameraManager;
//import com.redstar.mainapp.frame.block.scanblock.zxing.decoding.CaptureActivityHandler;
//import com.redstar.mainapp.frame.block.scanblock.zxing.decoding.InactivityTimer;
//import com.redstar.mainapp.frame.block.scanblock.zxing.view.ViewfinderView;
//import com.redstar.mainapp.frame.utils.easypermissions.AfterPermissionGranted;
//import com.redstar.mainapp.frame.utils.easypermissions.EasyPermissions;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//
///**
// * Initial the camera
// *
// * @author sujc
// */
//public class ScanActivity extends BaseActivity implements Callback {
//
//    private CaptureActivityHandler handler;
//    private ViewfinderView viewfinderView;
//    private boolean hasSurface;
//    private Vector<BarcodeFormat> decodeFormats;
//    private String characterSet;
//    private InactivityTimer inactivityTimer;
//    private MediaPlayer mediaPlayer;
//    private boolean playBeep;
//    private static final float BEEP_VOLUME = 0.10f;
//    private boolean vibrate;
//    public static final String REQUEST = "result";
//    private String permissions[] = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE};
//
//    @Override
//    protected int setBody() {
//        return R.layout.scan_activity_layout;
//    }
//
//    @Override
//    protected void initValue(Bundle onSavedInstance) {
//        super.initValue(onSavedInstance);
//        CameraManager.init(getApplication());
//        hasSurface = false;
//        inactivityTimer = new InactivityTimer(this);
//    }
//
//    @Override
//    protected void initWidget(Bundle onSavedInstance) {
//        super.initWidget(onSavedInstance);
//        setTitle(R.string.scan_title_text);
//        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
//    }
//
//
//    @SuppressWarnings("deprecation")
//    @Override
//    protected void onResume() {
//        super.onResume();
//        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
//        SurfaceHolder surfaceHolder = surfaceView.getHolder();
//        if (hasSurface) {
//            requestPermissions(surfaceHolder);
//        } else {
//            surfaceHolder.addCallback(this);
//            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        }
//        decodeFormats = null;
//        characterSet = null;
//
//        playBeep = true;
//        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
//        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
//            playBeep = false;
//        }
//        initBeepSound();
//        vibrate = true;
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (handler != null) {
//            handler.quitSynchronously();
//            handler = null;
//        }
//        CameraManager.get().closeDriver();
//    }
//
//    @Override
//    protected void onDestroy() {
//        inactivityTimer.shutdown();
//        super.onDestroy();
//    }
//
//    /**
//     * Handler scan result
//     *
//     * @param result
//     * @param barcode
//     */
//    public void handleDecode(Result result, Bitmap barcode) {
//        inactivityTimer.onActivity();
//        playBeepSoundAndVibrate();
//        String resultString = result.getText();
//        //FIXME
//        if (resultString.equals("")) {
//            Toast.makeText(ScanActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
//            ScanActivity.this.finish();
//        } else {
////            Toast.makeText(ScanActivity.this, "Result:" + resultString, Toast.LENGTH_SHORT).show();
////            Intent resultIntent = new Intent();
////            resultIntent.setClass(mContext,ScanResultActivity.class);
////            Bundle bundle = new Bundle();
//            Intent i = new Intent();
//            i.putExtra(REQUEST, resultString);
//            setResult(RESULT_OK, i);
//            finish();
////            bundle.putString(REQUEST, resultString);
////            resultIntent.putExtras(bundle);
////            //this.setResult(RESULT_OK, resultIntent);
////            startActivity(resultIntent);
//
//        }
//    }
//
//    private void initCamera(SurfaceHolder surfaceHolder) {
//        try {
//            CameraManager.get().openDriver(surfaceHolder);
//        } catch (IOException ioe) {
//            return;
//        } catch (RuntimeException e) {
//            return;
//        }
//        if (handler == null) {
//            handler = new CaptureActivityHandler(this, decodeFormats,
//                    characterSet);
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width,
//                               int height) {
//
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        if (!hasSurface) {
//            hasSurface = true;
//            requestPermissions(holder);
//        }
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        hasSurface = false;
//
//    }
//
//    public ViewfinderView getViewfinderView() {
//        return viewfinderView;
//    }
//
//    public Handler getHandler() {
//        return handler;
//    }
//
//    public void drawViewfinder() {
//        viewfinderView.drawViewfinder();
//
//    }
//
//    private void initBeepSound() {
//        if (playBeep && mediaPlayer == null) {
//            // The volume on STREAM_SYSTEM is not adjustable, and users found it
//            // too loud,
//            // so we now play on the music stream.
//            setVolumeControlStream(AudioManager.STREAM_MUSIC);
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setOnCompletionListener(beepListener);
//
//            AssetFileDescriptor file = getResources().openRawResourceFd(
//                    R.raw.beep);
//            try {
//                mediaPlayer.setDataSource(file.getFileDescriptor(),
//                        file.getStartOffset(), file.getLength());
//                file.close();
//                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                mediaPlayer = null;
//            }
//        }
//    }
//
//    private static final long VIBRATE_DURATION = 200L;
//
//    private void playBeepSoundAndVibrate() {
//        if (playBeep && mediaPlayer != null) {
//            mediaPlayer.start();
//        }
//        if (vibrate) {
//            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//            vibrator.vibrate(VIBRATE_DURATION);
//        }
//    }
//
//    /**
//     * When the beep has finished playing, rewind to queue up another one.
//     */
//    private final OnCompletionListener beepListener = new OnCompletionListener() {
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            mediaPlayer.seekTo(0);
//        }
//    };
//
//
//    @AfterPermissionGranted(PERMISSION_REQUEST_CODE+1)
//    private void requestPermissions(SurfaceHolder surfaceHolder) {
//        if (EasyPermissions.hasPermissions(this, permissions)) {
//            initCamera(surfaceHolder);
//        } else {
//            EasyPermissions.requestPermissions(mContext, mContext.getString(R.string.permission_remind), R.string.open_permission, R.string.cancel, PERMISSION_REQUEST_CODE, permissions);
//        }
//    }
//
//}