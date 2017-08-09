package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Point point1 = new Point(200, 200);
  Point point2 = new Point(600, 200);

  Camera mCamera = new Camera();
  Matrix mMatrix = new Matrix();

  public Practice12CameraRotateFixedView(Context context) {
    super(context);
  }

  public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int width = bitmap.getWidth();
    int height = bitmap.getHeight();

    int center1X = point1.x + width / 2;
    int center1Y = point1.y + height / 2;

    int center2X = point2.x + width / 2;
    int center2Y = point2.y + height / 2;

    canvas.save();
    mCamera.save();
    mMatrix.reset();

    mCamera.rotateX(30);
    mCamera.getMatrix(mMatrix);

    mMatrix.preTranslate(-center1X, -center1Y);
    mMatrix.postTranslate(center1X, center1Y);

    canvas.concat(mMatrix);
    canvas.drawBitmap(bitmap, point1.x, point1.y, paint);

    mCamera.restore();
    canvas.restore();

    /////////

    canvas.save();
    mCamera.save();
    mMatrix.reset();

    mCamera.rotateY(30);
    mCamera.getMatrix(mMatrix);

    mMatrix.preTranslate(-center2X, -center2Y);
    mMatrix.postTranslate(center2X, center2Y);

    canvas.concat(mMatrix);
    canvas.drawBitmap(bitmap, point2.x, point2.y, paint);

    mCamera.restore();
    canvas.restore();
  }
}
