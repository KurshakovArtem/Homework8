package exceptions

class IdRangeOutOfBoundsException(message: String = "Указан неверный диапазон Id") : RuntimeException(message)

class IdOutOfBoundsException(message: String = "Указан неверный Id") : RuntimeException(message)

class UnusedFunctionException(message: String = "Невозможно восстановить") : RuntimeException(message)