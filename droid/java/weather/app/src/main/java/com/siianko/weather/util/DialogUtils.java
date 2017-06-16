package com.siianko.weather.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by yevhenii.siianko on 6/7/17.
 */

public abstract class DialogUtils {

    public static Dialog getOneButtonDialog(@NonNull Context context,
                                            String dialogTitle,
                                            String dialogMessage,
                                            String buttonTitle,
                                            DialogInterface.OnClickListener buttonOnClickListener) {

        return createOneButtonBuilder(context,
                dialogTitle,
                dialogMessage,
                buttonTitle,
                buttonOnClickListener,
                null).create();
    }

    public static Dialog getOneButtonDialog(@NonNull Context context,
                                            String dialogTitle,
                                            String dialogMessage,
                                            String buttonTitle,
                                            DialogInterface.OnClickListener buttonOnClickListener,
                                            @NonNull View view) {

        return createOneButtonBuilder(context,
                dialogTitle,
                dialogMessage,
                buttonTitle,
                buttonOnClickListener,
                view).create();
    }

    public static Dialog getOneButtonDialog(@NonNull Context context,
                                            int dialogTitleId,
                                            int dialogMessageId,
                                            int buttonTitleId,
                                            DialogInterface.OnClickListener buttonOnClickListener) {

        return createOneButtonBuilder(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId),
                context.getString(buttonTitleId),
                buttonOnClickListener,
                null).create();
    }

    public static Dialog getOneButtonDialog(@NonNull Context context,
                                            int dialogTitleId,
                                            int dialogMessageId,
                                            int buttonTitleId,
                                            DialogInterface.OnClickListener buttonOnClickListener,
                                            int viewId) {

        return createOneButtonBuilder(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId),
                context.getString(buttonTitleId),
                buttonOnClickListener,
                LayoutInflater.from(context).inflate(viewId, null)).create();
    }

    public static Dialog getTwoButtonsDialog(@NonNull Context context,
                                             String dialogTitle,
                                             String dialogMessage,
                                             String positiveButtonTitle,
                                             DialogInterface.OnClickListener positiveButtonOnClickListener,
                                             String negativeButtonTitle,
                                             DialogInterface.OnClickListener negativeButtonOnClickListener) {

        return createTwoButtonsBuilder(context,
                dialogTitle,
                dialogMessage,
                positiveButtonTitle,
                positiveButtonOnClickListener,
                negativeButtonTitle,
                negativeButtonOnClickListener,
                null).create();
    }

    public static Dialog getTwoButtonsDialog(@NonNull Context context,
                                             String dialogTitle,
                                             String dialogMessage,
                                             String positiveButtonTitle,
                                             DialogInterface.OnClickListener positiveButtonOnClickListener,
                                             String negativeButtonTitle,
                                             DialogInterface.OnClickListener negativeButtonOnClickListener,
                                             @NonNull View view) {

        return createTwoButtonsBuilder(context,
                dialogTitle,
                dialogMessage,
                positiveButtonTitle,
                positiveButtonOnClickListener,
                negativeButtonTitle,
                negativeButtonOnClickListener,
                view).create();
    }

    public static Dialog getTwoButtonsDialog(@NonNull Context context,
                                             int dialogTitleId,
                                             int dialogMessageId,
                                             int positiveButtonTitleId,
                                             DialogInterface.OnClickListener positiveButtonOnClickListener,
                                             int negativeButtonTitleId,
                                             DialogInterface.OnClickListener negativeButtonOnClickListener) {

        return createTwoButtonsBuilder(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId),
                context.getString(positiveButtonTitleId),
                positiveButtonOnClickListener,
                context.getString(negativeButtonTitleId),
                negativeButtonOnClickListener,
                null).create();
    }

    public static Dialog getTwoButtonsDialog(@NonNull Context context,
                                             int dialogTitleId,
                                             int dialogMessageId,
                                             int positiveButtonTitleId,
                                             DialogInterface.OnClickListener positiveButtonOnClickListener,
                                             int negativeButtonTitleId,
                                             DialogInterface.OnClickListener negativeButtonOnClickListener,
                                             int viewId) {

        return createTwoButtonsBuilder(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId),
                context.getString(positiveButtonTitleId),
                positiveButtonOnClickListener,
                context.getString(negativeButtonTitleId),
                negativeButtonOnClickListener,
                LayoutInflater.from(context).inflate(viewId, null)).create();
    }

    public static Dialog getThreeButtonsDialog(@NonNull Context context,
                                               String dialogTitle,
                                               String dialogMessage,
                                               String positiveButtonTitle,
                                               DialogInterface.OnClickListener positiveButtonOnClickListener,
                                               String neutralButtonTitle,
                                               DialogInterface.OnClickListener neutralButtonOnClickListener,
                                               String negativeButtonTitle,
                                               DialogInterface.OnClickListener negativeButtonOnClickListener) {

        return createThreeButtonsBuilder(context,
                dialogTitle,
                dialogMessage,
                positiveButtonTitle,
                positiveButtonOnClickListener,
                neutralButtonTitle,
                neutralButtonOnClickListener,
                negativeButtonTitle,
                negativeButtonOnClickListener,
                null).create();
    }

    public static Dialog getThreeButtonsDialog(@NonNull Context context,
                                               String dialogTitle,
                                               String dialogMessage,
                                               String positiveButtonTitle,
                                               DialogInterface.OnClickListener positiveButtonOnClickListener,
                                               String neutralButtonTitle,
                                               DialogInterface.OnClickListener neutralButtonOnClickListener,
                                               String negativeButtonTitle,
                                               DialogInterface.OnClickListener negativeButtonOnClickListener,
                                               @NonNull View view) {

        return createThreeButtonsBuilder(context,
                dialogTitle,
                dialogMessage,
                positiveButtonTitle,
                positiveButtonOnClickListener,
                neutralButtonTitle,
                neutralButtonOnClickListener,
                negativeButtonTitle,
                negativeButtonOnClickListener,
                view).create();
    }

    public static Dialog getThreeButtonsDialog(@NonNull Context context,
                                               int dialogTitleId,
                                               int dialogMessageId,
                                               int positiveButtonTitleId,
                                               DialogInterface.OnClickListener positiveButtonOnClickListener,
                                               int neutralButtonTitleId,
                                               DialogInterface.OnClickListener neutralButtonOnClickListener,
                                               int negativeButtonTitleId,
                                               DialogInterface.OnClickListener negativeButtonOnClickListener) {

        return createThreeButtonsBuilder(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId),
                context.getString(positiveButtonTitleId),
                positiveButtonOnClickListener,
                context.getString(neutralButtonTitleId),
                neutralButtonOnClickListener,
                context.getString(negativeButtonTitleId),
                negativeButtonOnClickListener,
                null).create();
    }

    public static Dialog getThreeButtonsDialog(@NonNull Context context,
                                               int dialogTitleId,
                                               int dialogMessageId,
                                               int positiveButtonTitleId,
                                               DialogInterface.OnClickListener positiveButtonOnClickListener,
                                               int neutralButtonTitleId,
                                               DialogInterface.OnClickListener neutralButtonOnClickListener,
                                               int negativeButtonTitleId,
                                               DialogInterface.OnClickListener negativeButtonOnClickListener,
                                               int viewId) {

        return createThreeButtonsBuilder(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId),
                context.getString(positiveButtonTitleId),
                positiveButtonOnClickListener,
                context.getString(neutralButtonTitleId),
                neutralButtonOnClickListener,
                context.getString(negativeButtonTitleId),
                negativeButtonOnClickListener,
                LayoutInflater.from(context).inflate(viewId, null)).create();
    }

    public static ProgressDialog getProgressDialog(@NonNull Context context,
                                                   String dialogTitle,
                                                   String dialogMessage) {

        return createProgressDialog(context,
                dialogTitle,
                dialogMessage);
    }

    public static ProgressDialog getProgressDialog(@NonNull Context context,
                                                   int dialogTitleId,
                                                   int dialogMessageId) {

        return createProgressDialog(context,
                context.getString(dialogTitleId),
                context.getString(dialogMessageId));
    }

    private static AlertDialog.Builder createOneButtonBuilder(@NonNull Context context,
                                                              String dialogTitle,
                                                              String dialogMessage,
                                                              String buttonTitle,
                                                              DialogInterface.OnClickListener buttonOnClickListener,
                                                              View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(dialogTitle)
                .setMessage(dialogMessage)
                .setPositiveButton(buttonTitle, buttonOnClickListener);

        if (view != null) {

            builder.setView(view);
        }

        return builder;
    }

    private static AlertDialog.Builder createTwoButtonsBuilder(@NonNull Context context,
                                                               String dialogTitle,
                                                               String dialogMessage,
                                                               String positiveButtonTitle,
                                                               DialogInterface.OnClickListener positiveButtonOnClickListener,
                                                               String negativeButtonTitle,
                                                               DialogInterface.OnClickListener negativeButtonOnClickListener,
                                                               View view) {

        AlertDialog.Builder alertDialogBuilder = createOneButtonBuilder(context,
                dialogTitle,
                dialogMessage,
                positiveButtonTitle,
                positiveButtonOnClickListener,
                view);

        alertDialogBuilder.setNeutralButton(negativeButtonTitle, negativeButtonOnClickListener);

        return alertDialogBuilder;
    }

    private static AlertDialog.Builder createThreeButtonsBuilder(@NonNull Context context,
                                                                 String dialogTitle,
                                                                 String dialogMessage,
                                                                 String positiveButtonTitle,
                                                                 DialogInterface.OnClickListener positiveButtonOnClickListener,
                                                                 String neutralButtonTitle,
                                                                 DialogInterface.OnClickListener neutralButtonOnClickListener,
                                                                 String negativeButtonTitle,
                                                                 DialogInterface.OnClickListener negativeButtonOnClickListener,
                                                                 View view) {

        AlertDialog.Builder alertDialogBuilder = createTwoButtonsBuilder(context,
                dialogTitle,
                dialogMessage,
                positiveButtonTitle,
                positiveButtonOnClickListener,
                negativeButtonTitle,
                negativeButtonOnClickListener,
                view);

        alertDialogBuilder.setNeutralButton(neutralButtonTitle, neutralButtonOnClickListener);

        return alertDialogBuilder;
    }

    private static ProgressDialog createProgressDialog(@NonNull Context context,
                                                       String dialogTitle,
                                                       String dialogMessage) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(dialogTitle);
        progressDialog.setMessage(dialogMessage);

        return progressDialog;
    }
}
