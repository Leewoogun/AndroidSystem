package com.lwg.util

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BiometricUtil(
    private val activity: FragmentActivity,
) {
    private val biometricManager = BiometricManager.from(activity)
    private val executor = ContextCompat.getMainExecutor(activity)

    fun checkUseBiometric(
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                authenticate(
                    onSuccess = onSuccess,
                    onFailure = onFailure
                )
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Logger.e("이 기기에는 생체 인증 하드웨어가 없어요")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Logger.e("생체 인증 하드웨어를 현재 사용할 수 없어요")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                Logger.e("사용자가 생체 인증을 설정하지 않았어요")
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED ->
                Logger.e("보안 업데이트가 필요해요")
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED ->
                Logger.e("현재 설정으로는 생체 인증을 사용할 수 없어요")
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN ->
                Logger.e("알 수 없는 상태예요")
        }
    }

    private fun authenticate(
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    onSuccess()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    onFailure()
                }

                override fun onAuthenticationFailed() {
                    onFailure()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("생체 정보로 인증해주세요")
            .setNegativeButtonText("다음에 인증")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}