# 아오바카페랭 - 카페 주문식 프로그래밍 언어
⚠️⚠️ 이 언어는 아직 **개발 중** 입니다! ⚠️⚠️

## 1. 소개
아오바카페랭(AobaCafeLang)은 카페에서 주문을 하듯 프로그래밍을 할 수 있게 설계한 프로그래밍 언어입니다.   
본 언어는 엄랭, 몰랭 등 다양한 **Esoteric programming language (Esolang)**&ZeroWidthSpace;들을 보고 "와, 나도 하나 
만들어보고 싶다!"는 생각에 만들어진 언어입니다.   
다른 언어들을 보고 참고해서 만들어보려 했지만 사실상 문법을 이해하지 못하겠어서 (아직 대학생 개발자 지망생이라) 그냥 아는
대로 JAVA 공부한 것을 바탕으로 만들어보고 있는지라 **부족한 점이 매우 많을 수 있습니다.**   
그럼에도 이것저것 코드를 찾아보면서 연구하고 있으면서 개발 중에 있습니다.   
~~대체로 다른 esolang들을 찾아보고 작동 방식을 연구하면서 java로 변환해보거나 java 코드를 뜯어보는 방식이긴 합니다...~~   
부족한 것이 많지만, 재미로 만들기 시작한 것이므로 재미로 봐주시고 충고도 언제든지 환영합니다!

## 2. 문법 (수정 중)
### 1) 기본
- 모든 코드의 시작은 `주문할게요`로 시작합니다.
- 모든 코드는 `여기까지 해서 얼마에요`로 끝냅니다.
- 시작, 끝, 개행 명령을 제외한 모든 함수는 `주세요`로 끝냅니다.

### 2) 변수
#### 변수 선언
변수는 이름을 정할 수 있고, 이름은 공백 없는 한글 혹은 영단어로 지을 수 있습니다.   
변수명은 **카페에서 주문할 수 있는 음료 이름**&ZeroWidthSpace;을 권장합니다.   

변수는 `int`형에 해당하는 `톨 사이즈`와 `long`에 해당하는 `그란데 사이즈`가 있습니다.   
```
톨 사이즈로 아메리카노 10ml 주세요 // int 아메리카노 = 10;
그란데 사이즈로 카페라떼 150ml 주세요 // long 카페라떼 = 150;
```
그래서 `(변수 유형)로 (변수명) (초기값)ml 주세요`를 입력해서 변수를 선언할 수 있습니다.   
변수 값이 범위를 초과하는 경우 예외(Exception)가 발생합니다. 아래 **예외 처리**&ZeroWidthSpace;를 확인하세요.

### 3) 연산
- 모든 연산의 시작은 `변수 이름`으로 시작해 결과값을 저장합니다.
- 모든 연산의 마지막은 `해주세요` 로 공손하게 부탁합니다!   
- 공손하지 않은 연산을 부탁하면 ~~알바생도 사람이라서~~ 무시합니다!

#### 증감 연산자 (++, --)
변수의 값을 1 증가시키거나 1 감소 시키는 증감 연산자를 사용할 수 있습니다.   
사용하기 위해 변수를 맨 앞에서 선언하고 `샷 추가`를 요청하면 1을 증가시킵니다.
`시럽 빼고`를 요청하면 1을 감소시킵니다.
```
톨 사이즈로 카페라떼 72ml 주세요
그란데 사이즈로 돌체라떼 100ml 주세요
카페라떼 시럽 빼고 해주세요 // 72에서 1을 뺀 71이 됨
돌체라떼 샷 추가 해주세요 // 100에서 1을 더한 101이 됨
```
증감 연산자는 여러 번 반복해서 `샷 추가 샷 추가 샷 추가` 혹은 `시럽 빼고 샷 추가 시럽 빼고` 처럼 사용할 수 있습니다.
```
톨 사이즈로 카페라떼 100ml 주세요
카페라떼 시럽 빼고 샷 추가 샷 추가 해주세요 // 100-1+1+1 = 101이 됨
```

### 4) 출력
출력 함수의 기본형은 `이렇게 주문할게요`입니다.

#### 변수 출력
`이렇게 주문할게요` 뒤에 변수를 붙여서 사용합니다.
- 변수의 숫자 값을 출력하며, 띄어서 변수들의 이름을 나열합니다.
- 다만, 변수 중간에 띄어쓰기가 **실제 콘솔의 띄어쓰기를 의미하지는 않습니다.**
- `톨 사이즈`와 `그란데 사이즈`에 관계 없이 사용이 가능합니다.
```
톨 사이즈로 아메리카노 10ml 주세요
그란데 사이즈로 카페라떼 150ml 주세요
이렇게 주문할게요 카페라떼 아메리카노 // 10150 출력
```

#### 변수를 아스키코드로 변환 후 출력
`이렇게 주문할게요 캐리어에 담을 것은` 뒤에 변수를 붙여서 사용하고 뒤에 `에요`를 붙입니다.
- 변수의 숫자 값을 **아스키코드로 변환** 후 출력합니다.
- 사용 방식은 `이렇게 주문할게요`와 다르지 않습니다. 변수를 띄어쓰기로 모두 나열합니다.
- 동일하게 변수 간의 띄어쓰기 구분이 **실제 콘솔의 띄어쓰기를 의미하지는 않습니다.**
- 끝에 `에요`는 변수와 띄어쓰기를 해서 사용합니다.
- 다만, `그란데 사이즈`는 **캐리어에 담을 수 없습니다.**
```
톨 사이즈로 카페라떼 72ml 주세요
톨 사이즈로 에스프레소 101ml 주세요
이렇게 주문할게요 캐리어에 담을 것은 카페라떼 에스프레소 에요 // He 출력
```

#### 개행 문자(`\n`) 출력
한 줄로 구분하여 `주문이 길어졌는데`를 사용합니다.   
윗부분과 같이 `톨 사이즈 카페라떼`와 `톨 사이즈 에스프레소`가 선언되었다면,
```
이렇게 주문할게요 카페라떼 // 72 출력
주문이 길어졌는데 // 개행
이렇게 주문할게요 캐리어에 담을 것은 카페라떼 에스프레소 // He 출력
```
위와 같이 작동하여
```
72
He
```
로 출력됩니다.

### 5) 지시문
### 해당하는 줄로 이동
카페 알바생들은 ~~여러분의 이상한 주문도 다 기억할 정도로~~ 기억력이 좋습니다!   
기억력이 좋지 않은 당신 대신 카페 알바생이 당신의 주문을 기억하고 해당 주문으로 이동해줍니다!
- 입력한 모든 코드의 길이에 대하여 `N`줄이라 할 때 이 `N`을 `int`형으로 받아들입니다.
- `제가 M 번째 주문한 게 뭐였죠` 문법을 활용하여 **`M`번째 줄로 이동합니다.**
- 이동하는 줄을 세는 것은 **시작이 첫 번째 줄**&ZeroWidthSpace;입니다. 배열처럼 0으로 시작하지 않음에 주의합니다!
```
주문할게요
톨 사이즈로 아메리카노 72ml 주세요
제가 5 번째 주문한 게 뭐였죠 // 5번째 줄로 이동 (4번째 줄은 무시, index 단위로 0부터 시작하는 것이 아닙니다!)
아메리카노 샷 추가 해주세요 // 무시, 실행되지 않습니다
아메리카노 시럽 빼고 해주세요 // 1 감소하여 아메리카노의 값이 71이 됩니다
```
- '주문할게요'로 돌아갈 수 없고, 주문하지 않은 범위로 이동할 수 없습니다. **(1 < M < N)**
- 해당 범위가 아닌 위치로 이동하려고 한다면 예외(Exception)가 발생합니다. 아래 **예외 처리**&ZeroWidthSpace;를 확인하세요.

### 6) 예외 처리
- 규칙의 코드 마지막에 적어야 하는 `여기까지 해서 얼마에요`가 없는 경우, `뒤에 손님들이 밀려서, 주문이 아직 정해지지 않으셨으면 결정하고 오시겠어요?`
- `톨 사이즈` 혹은 `그란데 사이즈`로 주문할 수 없는 용량을 주문한 경우 `그 정도 양은 주문하신 사이즈에 드릴 수 없어요.`가 출력됩니다.
- 없는 변수에 대해서는 접근할 수 없기 때문에, `그 메뉴는 없어서 주문이 불가능하세요.`가 출력됩니다.
- 각 연산의 기본 조건에 해당하는 `해주세요`가 없는 등 부자연스러운 명령이 할당된 경우, `주문이 틀리셨는데 DM으로 보내드린 메뉴판 다시 확인해주시고 주문하시겠어요?`가 출력됩니다.
- 이동할 수 없는 범위로 이동하려고 한다면, `그런 주문은 아직 하신 적 없으신 것 같은데요?`가 출력됩니다.

## 3. 히스토리
- 2025.03.12 : 아오바카페랭 아이디어 정리, 기본 입출력 및 변수형(해당 예외 처리) 구현, github 레포지토리 작성 및 업로드
- 2025.03.13
  - git 활용 파일 연동 및 main.java에서 D://cafelang 주소를 통해 파일 찾기가 가능하게 구현
  - 증감 연산자(샷 추가, 시럽 빼고) 구현 및 규칙에 대한 일부 예외 처리 추가
  - 지정 줄로 이동하여 코드를 진행하는 goto문 구현

## 4. TODO
- goto문 오용을 방지하기 위한 timeout 설정