public enum RomanNumeralDozen {
    X(1), XX(2), XXX(3), XL(4), L(5),
    LX(6), LXX(7), LXXX(8), XC(9), C(10);

    final int arabicNumberDozen;

    RomanNumeralDozen(int arabicNumberDozen) {
        this.arabicNumberDozen = arabicNumberDozen;
    }

}