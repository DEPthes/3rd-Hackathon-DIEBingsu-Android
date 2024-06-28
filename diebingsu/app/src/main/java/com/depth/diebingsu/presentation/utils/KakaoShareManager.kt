package com.depth.diebingsu.presentation.utils

import android.content.Context
import com.depth.diebingsu.core.util.LoggerUtils
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link

class KakaoShareManager(
    private val context: Context,
) {
    private lateinit var invitationTemplate: FeedTemplate
    private lateinit var resultTemplate: FeedTemplate

    fun doShare(type: String) {
        if (!ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            LoggerUtils.e("카카오톡 미설치")
            return
        }

        if(type == "invitation") sendInvitation()
        else sendResult()
    }

    private fun sendInvitation(){
        invitationTemplate = FeedTemplate(
            content = Content(
                title = "[WEAVE] 친구야 같이 미팅하자",
                description = "미팅 팀 초대장이 도착했어요!",
                imageUrl = "",
                link = Link(
//                    androidExecutionParams = mapOf(""),
//                    iosExecutionParams = mapOf("type" to "invitation", "code" to code, "userId" to myInfo!!.id),
//                    mobileWebUrl = "market://details?id=com.studentcenter.weave"
                ),
                imageHeight = 400,
                imageWidth = 400
            ),
            buttonTitle = "초대장 확인하기"
        )

        shareTemplate("invitation")
    }

    private fun sendResult(){
        resultTemplate = FeedTemplate(
            content = Content(
                title = "[WEAVE] 친구야 같이 미팅하자",
                description = "미팅 팀 초대장이 도착했어요!",
                imageUrl = "",
                link = Link(
//                    androidExecutionParams = mapOf(""),
//                    iosExecutionParams = mapOf("type" to "invitation", "code" to code, "userId" to myInfo!!.id),
//                    mobileWebUrl = "market://details?id=com.studentcenter.weave"
                ),
                imageHeight = 400,
                imageWidth = 400
            ),
            buttonTitle = "초대장 확인하기"
        )

        shareTemplate("result")
    }


    private fun shareTemplate(type: String) {
        ShareClient.instance.shareDefault(
            context,
            if(type == "invitation") invitationTemplate else resultTemplate
        ) { sharingResult, sharingError ->
            when {
                sharingError != null -> {
                    LoggerUtils.e("${type}: 카카오톡 공유 실패: $sharingError")
                }
                sharingResult != null -> {
                    LoggerUtils.d("${type}: 카카오톡 공유 성공 ${sharingResult.intent}")
                    context.startActivity(sharingResult.intent)

                    logWarnings(sharingResult.warningMsg, sharingResult.argumentMsg)
                }
            }
        }
    }

    private fun logWarnings(warningMsg: Map<String, String>, argumentMsg: Map<String, String>) {
        if (warningMsg.isNotEmpty()) LoggerUtils.w("Warning Msg: $warningMsg")
        if (argumentMsg.isNotEmpty()) LoggerUtils.w("Argument Msg: $argumentMsg")
    }
}