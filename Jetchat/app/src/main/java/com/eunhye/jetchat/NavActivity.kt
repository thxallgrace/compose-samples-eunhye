package com.eunhye.jetchat

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.ViewCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.eunhye.jetchat.components.JetchatDrawer
import com.eunhye.jetchat.databinding.ContentMainBinding

class NavActivity : AppCompatActivity() {

    // by viewModels() : Activity/Fragment의 생명주기 범위에 맞게 ViewModel 인스턴스를 자동으로 생성/재사용해 줌.
    // 해당 범위에 동일한 ViewModel이 있으면 기존 인스턴스를 반환하고, 없으면 새로 생성
    // 왜 "by" ? 코틀린 위임 프로퍼티를 이용해서 getValue 호출시 자동으로 ViewModelProvider에서 인스턴스를 가져오는 역할을 해줌
    // 실제로는 val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    private val viewModel : MainViewModel by viewModels()

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
            // Compose에서 서랍(드로워)의 상태를 관리하는 객체
            // 초기값이 닫힘 설정
            // rememberDrawerState를 통해 상태를 컴포즈가 기억하게 함
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            // viewModel의 stateflow값을 Compose Lifecycle에 맞춰서 실시간으로 관찰
            val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()
            var selectedMenu by remember { mutableStateOf("composers") }

            if(drawerOpen) {
                // 컴포즈내에서 부수효과(side effect)를 실행하는 컴포저블 빌드시 하나의 코루틴을 실행
                // 코루틴을 실행한다 : Compose 내에서 비동기 작업이나 시간이 걸리는 작업 (네트워크 호출, 애니메이션, 딜레이 등)응 경량의 스레드 단위인 코루틴 형태로 실행한다
                // 컴포저블 수명 주기에 맞춰 자동으로 관리되면 코루틴을 실행
                // key가 Unit으로 변하지 않기 떄문에 이 코루틴은 컴포저블이 처음 그려질 때 한번만 실행됩니다.
                // 컴포저블이 끝나면 자동으로 코루틴 취소 > 리소스 누수 없이 안전
                LaunchedEffect(Unit) {
                    try {
                        drawerState.open()
                    } finally {
                        // 반복해서 열기 신호를 감지하는것을 방어하기 위함
                        viewModel.resetOpenDrawerAction()
                    }
                }
            }

            val scope = rememberCoroutineScope()

            JetchatDrawer(
                drawerState = drawerState
            ) {
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
