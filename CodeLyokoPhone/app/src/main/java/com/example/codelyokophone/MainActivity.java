package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity {

    // Request Permission for app
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_CONTACTS = 1;

    // Other
    String numberSelected;
    private int delayIncrement = 140;
    private ScheduledExecutorService scheduleTaskExecutor;
    Context context = this;

    // Methods
    public void PerformAnimation(int[] digitGetter, int i, int delayer, Handler handlerAnimation, int delayIncrement) {
        // Haven't found a way to optimise this yet
        if (digitGetter[i] == 1) {
            buttonOne.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonOne.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonOne.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonOne.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 2) {
            buttonTwo.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonTwo.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonTwo.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonTwo.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 3) {
            buttonThree.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonThree.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonThree.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonThree.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 4) {
            buttonFour.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonFour.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonFour.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonFour.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 5) {
            buttonFive.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonFive.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonFive.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonFive.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 6) {
            buttonSix.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonSix.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonSix.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonSix.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 7) {
            buttonSeven.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonSeven.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonSeven.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonSeven.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 8) {
            buttonEight.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonEight.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonEight.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonEight.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 9) {
            buttonNine.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonNine.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonNine.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonNine.setPressed(false);
                }
            }, delayer);
        } else if (digitGetter[i] == 0) {
            buttonZero.setPressed(true);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonZero.setPressed(true);
                }
            }, delayer - this.delayIncrement);
            buttonZero.setPressed(false);
            handlerAnimation.postDelayed(new Runnable() {
                public void run() {
                    buttonZero.setPressed(false);
                }
            }, delayer);
        }
    }
    public void openContactList() {
        Intent contactIntent = new Intent(this, ContactList.class);
        startActivityForResult(contactIntent, 1);
    }

    // Variables

    // EditText
    EditText numberInput;
    EditText downloadSpeed;
    EditText uploadSpeed;

    // Slide Options
    ImageButton buttonFwr;
    ImageButton buttonAsper;
    ImageButton buttonDone;

    // Top Options
    ImageButton buttonCall;
    ImageButton buttonClear;
    ImageButton buttonHang;

    // Bottom Options
    ImageButton buttonCrew;
    ImageButton buttonZero;
    ImageButton buttonHastag;

    // Numbers
    ImageButton buttonOne;
    ImageButton buttonTwo;
    ImageButton buttonThree;
    ImageButton buttonFour;
    ImageButton buttonFive;
    ImageButton buttonSix;
    ImageButton buttonSeven;
    ImageButton buttonEight;
    ImageButton buttonNine;

    // Main Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting Variable Instances
        numberInput = (EditText) findViewById(R.id.numberInput);
        buttonFwr = (ImageButton) findViewById(R.id.buttonFwr);
        buttonCall = (ImageButton) findViewById(R.id.buttonCall);
        buttonZero = (ImageButton) findViewById(R.id.buttonZero);
        buttonOne = (ImageButton) findViewById(R.id.buttonOne);
        buttonTwo = (ImageButton) findViewById(R.id.buttonTwo);
        buttonThree = (ImageButton) findViewById(R.id.buttonThree);
        buttonFour = (ImageButton) findViewById(R.id.buttonFour);
        buttonFive = (ImageButton) findViewById(R.id.buttonFive);
        buttonSix = (ImageButton) findViewById(R.id.buttonSix);
        buttonSeven = (ImageButton) findViewById(R.id.buttonSeven);
        buttonEight = (ImageButton) findViewById(R.id.buttonEight);
        buttonNine = (ImageButton) findViewById(R.id.buttonNine);
        buttonClear = (ImageButton) findViewById(R.id.buttonClear);
        buttonCrew = (ImageButton) findViewById(R.id.buttonCrew);
        downloadSpeed = (EditText) findViewById(R.id.downloadSpeed);
        uploadSpeed = (EditText) findViewById(R.id.uploadSpeed);
        ImageView footer = (ImageView) findViewById(R.id.footer);
        ProgressBar downloadRate = (ProgressBar) findViewById(R.id.downloadRate);
        ProgressBar uploadRate = (ProgressBar) findViewById(R.id.uploadRate);

        // Set Footer Animation
        AnimationDrawable footerAnimation;
        footer.setBackgroundResource(R.drawable.footeranimation);
        footerAnimation = (AnimationDrawable) footer.getBackground();

        // Set Number Input as Number Field with custom Font
        numberInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        // Set Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Applying font
        Typeface font = Typeface.createFromAsset(getAssets(), "font/Square.ttf");
        numberInput.setTypeface(font);
        downloadSpeed.setTypeface(font);
        uploadSpeed.setTypeface(font);

        // Enabling Read Only in numberInput
        numberInput.setEnabled(false);
        downloadSpeed.setEnabled(false);
        uploadSpeed.setEnabled(false);

        // Button Actions

        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("0");
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("1");
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("2");
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("3");
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("4");
            }
        });

        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("5");
            }
        });

        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("6");
            }
        });

        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("7");
            }
        });

        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("8");
            }
        });

        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("9");
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.setText("");
            }
        });

        // Open Contacts List

        buttonFwr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactList();
            }
        });

        // Internet Status
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        scheduleTaskExecutor = Executors.newScheduledThreadPool(5);
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {

                runOnUiThread(new Runnable() {
                    public void run() {
                        int downSpeed = nc.getLinkDownstreamBandwidthKbps() / 1024;
                        int upSpeed = nc.getLinkUpstreamBandwidthKbps() / 1024;
                        downloadSpeed.setText(Integer.toString(downSpeed) + "MB/SEC");
                        uploadSpeed.setText(Integer.toString(upSpeed) + "MB/SEC");

                        if (downSpeed >= 100) {
                            downloadRate.setProgress(100);
                        } else {
                            downloadRate.setProgress(downSpeed);
                        }

                        if (upSpeed >= 100) {
                            uploadRate.setProgress(100);
                        } else {
                            uploadRate.setProgress(upSpeed);
                        }

                        //Log.i("LOGCAT downSpeed: \n", Integer.toString(downSpeed));
                        //Log.i("LOGCAT upSpeed: \n", Integer.toString(upSpeed));
                    }
                });
            }
        }, 0, 5, TimeUnit.SECONDS);

        // Call Activity
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        // Call
        buttonCall.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                // Footer Animation
                footerAnimation.start();

                // Animation Before Call

                String stringNumber = numberInput.getText().toString();
                int intNumber = Integer.parseInt(stringNumber);
                int numberLength = stringNumber.length();
                int[] digitGetter = new int[15];

                Log.i("LOGCAT intNumber: ", Integer.toString(intNumber));
                Log.i("LOGCAT numberLength: ", Integer.toString(numberLength));

                int delayer = 0;

                if (stringNumber.trim().length() > 0) {

                    Handler handlerAnimation = new Handler();

                    for (int i = 0; i < numberLength; i++) {
                        digitGetter[i] = Integer.parseInt(Integer.toString(intNumber).substring(i, i+1));
                        delayer = delayer + delayIncrement;
                        PerformAnimation(digitGetter, i, delayer, handlerAnimation, delayIncrement);
                    }
                    delayer = delayer + delayIncrement;
                    buttonCall.setPressed(true);
                        handlerAnimation.postDelayed(new Runnable() {
                            public void run() {
                                buttonCall.setPressed(true);
                            }
                        }, delayer - delayIncrement);
                    buttonCall.setPressed(false);
                        handlerAnimation.postDelayed(new Runnable() {
                            public void run() {
                                buttonCall.setPressed(false);
                            }
                        }, delayer);
                    }

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS);
                } else {
                    // Make Phone Call
                    Handler handlerCall = new Handler();
                    handlerCall.postDelayed(new Runnable() {
                        public void run() {
                            if (stringNumber.trim().length() > 0) {
                                footerAnimation.stop();
                                callIntent.setData(Uri.parse("tel:"+stringNumber));
                                startActivity(callIntent);
                            } else {

                            }
                        }
                    }, delayer);
                }
            }
        });
    }

    // Get Phone Number From Contact List
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra("result");
                Log.d("LOGCAT", "Got back: " + result);
                result = result.replaceAll("-", "");
                result = result.replaceAll("[(]", "");
                result = result.replaceAll("[)]", "");
                result = result.replaceAll(" ", "");
                Log.d("LOGCAT", "Formated for Call: " + result);
                numberInput.setText(result);
                buttonCall.performClick();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("LOGCAT", "No Contacts Selected");
            }
        }
    }

}



