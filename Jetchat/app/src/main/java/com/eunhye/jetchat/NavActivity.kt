package com.eunhye.jetchat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.eunhye.jetchat.components.JetchatDrawer
import com.eunhye.jetchat.databinding.ContentMainBinding

class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // 뷰에 시스템 윈도 인셋 처리를 위한 콜백 리스너
        // 윈도우 인셋 : 상태바, 네비게이션바, IME 등 시스템 UI 컴포넌트로 인해 앱이 가려지는 영역 정보를 담고 있음
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets -> insets }

        // ComponentActivity 확장함수(setContent)로 내부적으로 컴포즈뷰를 만들어 주기 때문에 커스터마이징이 필요할때만 setContentView를 통해 컴포즈뷰 직접 생성
        // 커스터마이징의 예는 consumeWindowInsets = false와 같은걸 얘기함.
//        setContentView(
//            ComposeView(this).apply {
//                // consumeWindowInsets : ComposeView 에 전달되는 윈도우 인셋 (기본값은 true)
//                // false로 설정할 경우, ComposeView가 윈도우 인셋 이벤트를 소비하지 않고, 그대로 부모 뷰나 다른 곳에 전달
//                consumeWindowInsets = false
//            }
//        )

        setContent {
            JetchatDrawer() {
                // 기존 View기반 레이아웃(XML, ViewBinding)화면을 Compose영역에 삽입하는 것
                AndroidViewBinding(ContentMainBinding::inflate)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // [Navigation] 4. navigateUp 사용하여 액션 바에서 뒤로 가기 기능 추가
        return findNavController().navigateUp() || super.onSupportNavigateUp()
    }

    private fun findNavController() : NavController {
        // [Navigation] 3. NavController 생성
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
