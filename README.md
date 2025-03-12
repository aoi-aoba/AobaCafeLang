# 아오바카페랭 - 카페 주문식 프로그래밍 언어
⚠️⚠️ 이 언어는 아직 **개발 중** 입니다! ⚠️⚠️

## 1. 소개
아오바카페랭(AobaCafeLang)은 카페에서 주문을 하듯 프로그래밍을 할 수 있게 설계한 프로그래밍 언어입니다.   
본 언어는 엄랭, 몰랭 등 다양한 **Esoteric programming language (Esolang)**&ZeroWidthSpace;들을 보고 "와, 나도 하나 
만들어보고 싶다!"는 생각에 만들어진 언어입니다.   
다른 언어들을 보고 참고해서 만들어보려 했지만 사실상 문법을 이해하지 못하겠어서 (아직 대학생 개발자 지망생이라) 그냥 아는
대로 JAVA 공부한 것을 바탕으로 만들어보고 있는지라 **부족한 점이 매우 많을 수 있습니다.**   
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

### 3) 출력
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

### 4. 예외 처리
- `ClassCastException`과 `NumberFormatException`이 발생한 경우 "그 정도 양은 주문하신 사이즈에 드릴 수 없어요."가 출력됩니다.
- 없는 변수에 대해서는 접근할 수 없기 때문에, "그 메뉴는 없어서 주문이 불가능하세요."가 출력됩니다.
