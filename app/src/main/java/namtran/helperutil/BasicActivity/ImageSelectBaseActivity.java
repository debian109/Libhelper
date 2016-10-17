package namtran.helperutil.BasicActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import namtran.helperutil.BaseActivity;
import namtran.helperutil.R;


/**
 * Choose Image from Gallery or pick Camera and show ImageView
 */
public abstract class ImageSelectBaseActivity extends BaseActivity {
	/** Buttons for selector dialog */
	private View mBtnGallery = null, mBtnCamera = null, mBtnCancel = null;

	private final int REQ_CODE_PICK_GALLERY = 900001;
	private final int REQ_CODE_PICK_CAMERA = 900002;
	private final int REQ_CODE_PICK_CROP = 900003;

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean("mCropRequested", mCropRequested);
		savedInstanceState.putInt("mCropAspectWidth", mCropAspectWidth);
		savedInstanceState.putInt("mCropAspectHeight", mCropAspectHeight);
		BitmapDrawable drawable = (BitmapDrawable) ((ImageView) findViewById(R.id.ivImageSelected)).getDrawable();
		if (drawable != null) {
			Bitmap bitmap = drawable.getBitmap();
			savedInstanceState.putParcelable("bitmap", bitmap);
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mCropRequested = savedInstanceState.getBoolean("mCropRequested");
		mCropAspectWidth = savedInstanceState.getInt("mCropAspectWidth");
		mCropAspectHeight = savedInstanceState.getInt("mCropAspectHeight");
		Bitmap bm = (Bitmap) savedInstanceState.getParcelable("bitmap");
		if (bm != null) {
			((ImageView) findViewById(R.id.ivImageSelected)).setImageBitmap(bm);
		}
	}

	/**
	 * Call this to start!
	 */
	public void startSelectImage() {
		if (!checkWriteExternalPermission()) {
			showAlert("we need android.permission.WRITE_EXTERNAL_STORAGE");
			return;
		}
		if (!checkSDisAvailable()) {
			showAlert("Check External Storage.");
			return;
		}
		if (findViewById(R.id.ivImageSelected) == null) {
			showAlert("Your layout should have ImageView name as ivImageSelected.");
			return;
		}
		if (mBtnGallery == null) {
			setDefaultButtons();
		}
		setButtonsClick();
		showSelectDialog();
	}

	public File getSelectedImageFile() {
		return getTempImageFile();
	}

	private boolean mCropRequested = false;

	/**
	 * Setting this crop if necessary. If not set, no crop.
	 *
	 * @param width
	 *            crop size width.
	 * @param height
	 *            crop size height.
	 */
	private int mCropAspectWidth = 1, mCropAspectHeight = 1;

	public void setCropOption(int aspectX, int aspectY) {
		mCropRequested = true;
		mCropAspectWidth = aspectX;
		mCropAspectHeight = aspectY;
	}

	/**
	 * Maximum available image size settings. Horizontal, vertical box to adjust the image size to a smaller size than the
	 * size you specify. default size:
	 * 500
	 *
	 * @param sizePixel
	 *            basic 500
	 */
	private int mImageSizeBoundary = 500;

	public void setImageSizeBoundary(int sizePixel) {
		mImageSizeBoundary = sizePixel;
	}

	private boolean checkWriteExternalPermission() {
		String permission = "android.permission.WRITE_EXTERNAL_STORAGE";
		int res = checkCallingOrSelfPermission(permission);
		return (res == PackageManager.PERMISSION_GRANTED);
	}

	private boolean checkSDisAvailable() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * Set your own button views for selector dialog.
	 */
	public void setCustomButtons(View btnGallery, View btnCamera, View btnCancel) {
		if (btnGallery == null || btnCamera == null || btnCancel == null) {
			showAlert("All buttons should not null.");
		} else {
			mBtnGallery = btnGallery;
			mBtnCamera = btnCamera;
			mBtnCancel = btnCancel;
		}
	}

	/**
	 * Set default buttons for selector dialog, unless you set.
	 */
	private void setDefaultButtons() {
		Button btn1 = new Button(this);
		Button btn2 = new Button(this);
		Button btn3 = new Button(this);
		btn1.setText("From Gallery");
		btn2.setText("From Camera");
		btn3.setText("Cancel");
		mBtnGallery = btn1;
		mBtnCamera = btn2;
		mBtnCancel = btn3;
	}

	private File getTempImageFile() {
		File path = new File(Environment.getExternalStorageDirectory() + "/Android/Download/" + getPackageName() + "/temp/");
		if (!path.exists()) {
			path.mkdirs();
		}
		File file = new File(path, "tempimage.png");
		return file;
	}

	private void setButtonsClick() {
		mBtnGallery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSelectDialog.dismiss();
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_GALLERY);
			}
		});
		mBtnCamera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSelectDialog.dismiss();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempImageFile()));
				intent.putExtra("return-data", true);
				startActivityForResult(intent, REQ_CODE_PICK_CAMERA);
			}
		});
		mBtnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSelectDialog != null && mSelectDialog.isShowing()) {
					mSelectDialog.dismiss();
				}
			}
		});
	}

	private Dialog mSelectDialog;

	private void showSelectDialog() {
		if (mSelectDialog == null) {
			mSelectDialog = new Dialog(this);
			mSelectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			LinearLayout linear = new LinearLayout(this);
			linear.setOrientation(LinearLayout.VERTICAL);
			linear.addView(mBtnGallery);
			linear.addView(mBtnCamera);
			linear.addView(mBtnCancel);
			int dialogWidth = getResources().getDisplayMetrics().widthPixels / 2;
			if (dialogWidth / 2 > 700) {
				dialogWidth = 700;
			}
			mSelectDialog.setContentView(linear, new LayoutParams(dialogWidth, LayoutParams.WRAP_CONTENT));
		}
		mSelectDialog.show();
	}

	private void showAlert(String msg) {
		new AlertDialog.Builder(this).setTitle("Error").setMessage(msg).setPositiveButton(android.R.string.ok, null).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_CODE_PICK_GALLERY && resultCode == Activity.RESULT_OK) {
			// For the gallery directly on the uri Redirected data.
			try {
				Uri uri = data.getData();
				copyUriToFile(uri, getTempImageFile());
				if (mCropRequested) {
					cropImage();
				} else {
					doFinalProcess();
				}
			}catch (NullPointerException e){
				Toast.makeText(this, "Don't support file", Toast.LENGTH_LONG).show();
			}
		} else if (requestCode == REQ_CODE_PICK_CAMERA && resultCode == Activity.RESULT_OK) {
			// If the camera is returning results to file.
			// Camera rotation correction.
			correctCameraOrientation(getTempImageFile());
			if (mCropRequested) {
				cropImage();
			} else {
				doFinalProcess();
			}
		} else if (requestCode == REQ_CODE_PICK_CROP && resultCode == Activity.RESULT_OK) {
			// crop The results of returning to file.
			doFinalProcess();
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void doFinalProcess() {
		// sample size By applying a bitmap load.
		Bitmap bitmap = loadImageWithSampleSize(getTempImageFile());

		// image reduced to fit the image boundary size.
		bitmap = resizeImageWithinBoundary(bitmap);

		// It provides methods that can go get the results file.
		saveBitmapToFile(bitmap);

		// show image on ImageView
		Bitmap bm = BitmapFactory.decodeFile(getTempImageFile().getAbsolutePath());
		((ImageView) findViewById(R.id.ivImageSelected)).setImageBitmap(bm);
	}

	private void saveBitmapToFile(Bitmap bitmap) {
		File target = getTempImageFile();
		try {
			FileOutputStream fos = new FileOutputStream(target, false);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** After modifying the image size, rotation information when the camera is rotated compensation should. */
	private void correctCameraOrientation(File imgFile) {
		Bitmap bitmap = loadImageWithSampleSize(imgFile);
		try {
			ExifInterface exif = new ExifInterface(imgFile.getAbsolutePath());
			int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			int exifRotateDegree = exifOrientationToDegrees(exifOrientation);
			bitmap = rotateImage(bitmap, exifRotateDegree);
			saveBitmapToFile(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Bitmap rotateImage(Bitmap bitmap, int degrees) {
		if (degrees != 0 && bitmap != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
			try {
				Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != converted) {
					bitmap.recycle();
					bitmap = converted;
				}
			} catch (OutOfMemoryError ex) {
			}
		}
		return bitmap;
	}

	/**
	 * Method for converting a rotational angle of the EXIF information
	 * 
	 * @param exifOrientation
	 *            EXIF rotation angle
	 * @return The actual angle
	 */
	private int exifOrientationToDegrees(int exifOrientation) {
		if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
			return 90;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
			return 180;
		} else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
			return 270;
		}
		return 0;
	}

	/** An image of a desired size options. */
	private Bitmap loadImageWithSampleSize(File file) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		int width = options.outWidth;
		int height = options.outHeight;
		int longSide = Math.max(width, height);
		int sampleSize = 1;
		if (longSide > mImageSizeBoundary) {
			sampleSize = longSide / mImageSizeBoundary;
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = sampleSize;
		options.inPurgeable = true;
		options.inDither = false;

		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		return bitmap;
	}

	/**
	 * mImageSize Boundary size to resize the image. Image Size Boundary, if not less than resize
	 * Do 	.
	 */
	private Bitmap resizeImageWithinBoundary(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		if (width > height) {
			if (width > mImageSizeBoundary) {
				bitmap = resizeBitmapWithWidth(bitmap, mImageSizeBoundary);
			}
		} else {
			if (height > mImageSizeBoundary) {
				bitmap = resizeBitmapWithHeight(bitmap, mImageSizeBoundary);
			}
		}
		return bitmap;
	}

	private Bitmap resizeBitmapWithHeight(Bitmap source, int wantedHeight) {
		if (source == null)
			return null;

		int width = source.getWidth();
		int height = source.getHeight();

		float resizeFactor = wantedHeight * 1f / height;

		int targetWidth, targetHeight;
		targetWidth = (int) (width * resizeFactor);
		targetHeight = (int) (height * resizeFactor);

		Bitmap resizedBitmap = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, true);

		return resizedBitmap;
	}

	private Bitmap resizeBitmapWithWidth(Bitmap source, int wantedWidth) {
		if (source == null)
			return null;

		int width = source.getWidth();
		int height = source.getHeight();

		float resizeFactor = wantedWidth * 1f / width;

		int targetWidth, targetHeight;
		targetWidth = (int) (width * resizeFactor);
		targetHeight = (int) (height * resizeFactor);

		Bitmap resizedBitmap = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, true);

		return resizedBitmap;
	}

	private void copyUriToFile(Uri srcUri, File target) {
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		try {
			// Generating a stream
			inputStream = (FileInputStream) getContentResolver().openInputStream(srcUri);
			outputStream = new FileOutputStream(target);

			// Create channel
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();

			// Stream transmission over a channel
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fcout.close();
			} catch (IOException ioe) {
			}
			try {
				fcin.close();
			} catch (IOException ioe) {
			}
			try {
				outputStream.close();
			} catch (IOException ioe) {
			}
			try {
				inputStream.close();
			} catch (IOException ioe) {
			}
		}
	}

	private void cropImage() {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");
		List<ResolveInfo> cropToolLists = getPackageManager().queryIntentActivities(intent, 0);
		int size = cropToolLists.size();
		if (size == 0) {
			// No apps to handle the crop. Immediate treatment.
			doFinalProcess();
		} else {
			intent.setData(Uri.fromFile(getTempImageFile()));
			intent.putExtra("aspectX", mCropAspectWidth);
			intent.putExtra("aspectY", mCropAspectHeight);
			intent.putExtra("output", Uri.fromFile(getTempImageFile()));
			Intent i = new Intent(intent);
			ResolveInfo res = cropToolLists.get(0);
			i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
			startActivityForResult(i, REQ_CODE_PICK_CROP);
		}
	}
}
