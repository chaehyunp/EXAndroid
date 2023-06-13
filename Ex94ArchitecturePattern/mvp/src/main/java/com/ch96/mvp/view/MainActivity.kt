package com.ch96.mvp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.mvp.R
import com.ch96.mvp.databinding.ActivityMainBinding
import com.ch96.mvp.model.Item
import com.ch96.mvp.presernter.MainContract
import com.ch96.mvp.presernter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    
    //2. MVP [ Model - View - Presenter ] - view와 model의 완전 분리 특징이 가장 두드러짐 [ 뷰와 Presenter가 해야할 작업을 미리 인터페이스로 규격화 한것이 특징 - 모듈화된 작업 템플릿을 만들때 용이한 구조 ]
    //1) Model : MVC패턴의 모델과 같은 역할 [ 데이터 취급 : Item, Person, ItemModel... ]
    //2) View : 사용자가 볼 화면 및 이벤트 처리 [ activity_main.xml, MainActivity.kt, fragment_my.xml, MyFragment.kt ]
    //3) Presenter : View와 Model의 중계역할, 컨트롤러와 비슷하지만 인터페이스로 역할을 정해 놓음

    //주요특징 : 뷰와 프레젠터가 해야할 작업들을 미리 interface를 통해서 규격화

    //# view 참조변수
    lateinit var binding: ActivityMainBinding

    //# presenter 참조변수
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //# presenter 객체 생성 및 초기화
        presenter = MainPresenter()
        presenter.initial(this) //현재 view와 model이 null이므로 초기화

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //# view로써 사용자 이벤트 처리
        binding.btnSave.setOnClickListener { presenter.clickSave(binding.etName.text.toString(), binding.etEmail.text.toString()) }
        binding.btnLoad.setOnClickListener { presenter.clickLoad() }
    }

    override fun showData(item: Item) {
        binding.tv.text = "${item.name} : ${item.email}"
    }

    override fun getContext(): Context {
        return this
    }
}

// ## MVP 장점 ##
//1. MVC처럼 데이터를 제어하는 코드가 Activity나 Fragment 클래스 안에 존재하지 않으므로 간결
//2. MVC보다 조금 더 명확하게 각 역할별 코드가 잘 분리되어 작성됨
//3. 각 역할이 인터페이스로 규격화되어서 유지보수나 인수인계가 용이함
//4. view 안에서 model을 참조하고 있지 않기에 model의 변화에 영향받지않음
//5. 단위테스트에 용이

//## MVP 단점 ##
//1. MVC보다 만들어야할 기본 파일들이 많아 구조가 복잡해보임
//2. view와 presenter가 1:1로 대응되어 파일이 만들어짐 (화면 하나당 Activity,Contract,Presenter) - 파일이 엄청 많아짐
//3. 규모가 커지면 결국 presenter가 해야할 작업이 많아서 결국 비대해짐