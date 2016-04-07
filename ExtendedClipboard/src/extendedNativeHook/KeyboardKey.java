/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendedNativeHook;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 *
 * @author jgavilan
 */
public enum KeyboardKey {
    ESCAPE(1),
    F1(59),
    F2(60),
    F3(61),
    F4(62),
    F5(63),
    F6(64),
    F7(65),
    F8(66),
    F9(67),
    F10(68),
    F11(87),
    F12(88),
    F13(91),
    F14(92),
    F15(93),
    F16(99),
    F17(100),
    F18(101),
    F19(102),
    F20(103),
    F21(104),
    F22(105),
    F23(106),
    F24(107),
    BACKQUOTE(41),
    N1(2),
    N2(3),
    N3(4),
    N4(5),
    N5(6),
    N6(7),
    N7(8),
    N8(9),
    N9(10),
    N0(11),
    MINUS(12),
    EQUALS(13),
    BACKSPACE(14),
    TAB(15),
    CAPS_LOCK(58),
    A(30),
    B(48),
    C(46),
    D(32),
    E(18),
    F(33),
    G(34),
    H(35),
    I(23),
    J(36),
    K(37),
    L(38),
    M(50),
    N(49),
    O(24),
    P(25),
    Q(16),
    R(19),
    S(31),
    T(20),
    U(22),
    V(47),
    W(17),
    X(45),
    Y(21),
    Z(44),
    OPEN_BACKET(26),
    CLOSE_BRACKET(27),
    BACK_SLASH(43),
    SEMICOLON(39),
    QUOTE(40),
    ENTER(28),
    COMMA(51),
    PERIOD(52),
    SLASH(53),
    SPACE(57),
    PRINTSCREEN(3639),
    SCROLL_LOCK(70),
    PAUSE(3653),
    INSERT(3666),
    DELETE(3667),
    HOME(3655),
    END(3663),
    PAGE_UP(3657),
    PAGE_DOWN(3665),
    UP(57416),
    LEFT(57419),
    RIGHT(57421),
    DOWN(57424),
    NUM_LOCK(69),
    KP_DIVIDE(3637),
    KP_MULTIPLY(55),
    KP_SUBTRACT(74),
    KP_EQUALS(3597),
    KP_ADD(78),
    KP_ENTER(3612),
    KP_SEPARATOR(83),
    KP_1(79),
    KP_2(80),
    KP_3(81),
    KP_4(75),
    KP_5(76),
    KP_6(77),
    KP_7(71),
    KP_8(72),
    KP_9(73),
    KP_0(82),
    SHIFT_L(42),
    SHIFT_R(54),
    CONTROL_L(29),
    CONTROL_R(3613),
    ALT_L(56),
    ALT_R(3640),
    META_L(3675),
    META_R(3676),
    CONTEXT_MENU(3677),
    POWER(57438),
    SLEEP(57439),
    WAKE(57443),
    MEDIA_PLAY(57378),
    MEDIA_STOP(57380),
    MEDIA_PREVIOUS(57360),
    MEDIA_NEXT(57369),
    MEDIA_SELECT(57453),
    MEDIA_EJECT(57388),
    VOLUME_MUTE(57376),
    VOLUME_UP(57392),
    VOLUME_DOWN(57390),
    APP_MAIL(57452),
    APP_CALCULATOR(57377),
    APP_MUSIC(57404),
    APP_PICTURES(57444),
    BROWSER_SEARCH(57445),
    BROWSER_HOME(57394),
    BROWSER_BACK(57450),
    BROWSER_FORWARD(57449),
    BROWSER_STOP(57448),
    BROWSER_REFRESH(57447),
    BROWSER_FAVORITES(57446),
    KATAKANA(112),
    UNDERSCORE(115),
    FURIGANA(119),
    KANJI(121),
    HIRAGANA(123),
    YEN(125),
    KP_COMMA(126),
    SUN_HELP(65397),
    SUN_STOP(65400),
    SUN_PROPS(65398),
    SUN_FRONT(65399),
    SUN_OPEN(65396),
    SUN_FIND(65406),
    SUN_AGAIN(65401),
    SUN_UNDO(65402),
    SUN_COPY(65404),
    SUN_INSERT(65405),
    SUN_CUT(65403),
    UNDEFINED(0);
    
    private final int code;
    
    KeyboardKey(int n){
        this.code = n;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public String getKeyText(){
        return NativeKeyEvent.getKeyText(code);
    }
    
    public static KeyboardKey getKeyFromCode(int code){
        for( KeyboardKey key : KeyboardKey.values() )
            if( key.getCode() == code )
                return key;
        return UNDEFINED;
    }
    
    public static KeyboardKey getKey(NativeKeyEvent nke){
        return getKeyFromCode(nke.getKeyCode());
    }
    
}
