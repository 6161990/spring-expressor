### 디자인 패턴 
#### 종류 
![](../../../Library/Containers/com.majimakHARU.GrabIt/Data/2022-07-04_06-56-59.png)

* 언어에 종속적이지 않음
* 프레임워크 개발에 적용될 수 있음
* reuse, flexibility, extensibility, modularity

#### 객체 지향 설계
* Design Heuristics   
  * Abstract class vs. Concrete Class    
  * Class Inheritance vs. Object Composition   
  * Interface Inheritance vs, Implementation Inheritance Etc...   

#### 클래스 다이어그램 
![](../../../Library/Containers/com.majimakHARU.GrabIt/Data/2022-07-04_07-24-21.png)
* 연관관계 association
  * 클래스 상호 간에 서로 연관되어 있음을 나타냄
  * 단방향 연관관계의 경우는 화살표(->)로 표시함
  * 양방향 연관관계는 직선(-)으로 표시함
  * 클래스 간의 연관된 개체의 수를 표현해햐 하는 경우에는 선의 끝쪽에 다중성(multiplicity)를 나타냄
  
* 집합관계 (composition, aggregation)
  * 연관관계의 특별한 경우
  * 클래스 간의 포함관계를 나타냄
    * 전체 객체와 부분 객체의 인스턴스 생존 주기에 따라 두 가지로 구분됨(라이프 타임으로 구분한다)
      * 집약관계 
        * 클래스 다이어그램에 빈 마름모로 표시   
        * 전체 객체와 부분 객체의 라이프 타임이 독립적 즉, 포함하는 객체(전체 객체)가 사라져도 포함된 객체 (부분 객체)는 사라지지 않음   
        * 공유하는 리소스가 해당됨
        * 전체 객체가 생성될 때 매개 변수로 전달 받음 (밖에서 들어옴)
      * 합성관계
        * 클래스 다이어그램에 채워진 마름모로 표시   
        * 전체 객체의 라이프 타임에 부분객체가 종속됨. 전체 객체가 사라지면 부분 객체도 사라짐   
        * 주로 멤버 변수로 선언하여 사용 (안에서 생성함)
        * 전체 객체의 생성자에서 부분 객체를 생성함