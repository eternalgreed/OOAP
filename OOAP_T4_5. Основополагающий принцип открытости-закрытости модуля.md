Представим класс ReportGenerator, который должен генерировать отчёты разных форматов (PDF, Doc, и т.д.). Нам бы хотелось при добавлении нового формата не изменять "точку входа", расширяя его возможности новыми "модулями".

Мы можем определить интерфейс ReportGenerator с методом generate(String data), который будет реализован классами с различными стратегиями генерации отчетов:

```java
interface ReportGenerator {
    void generate(String data);
}
```

```java
class PDFReportGenerator implements ReportGenerator {
    @Override
    public void generate(String data) {
        // генерация PDF-отчёта
    }
}
```

```java
class DocReportGenerator implements ReportGenerator {
    @Override
    public void generate(String data) {
        // генерация Doc-отчёта
    }
}
```

Класс ReportGenerator должен работать в соответствии с переданной стратегией:

```java

class ReportGeneratorService {
    private ReportGenerator reportGenerator;

    public ReportGeneratorService(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    void generateReport(String data) {
        reportGenerator.generate(data);
    }
}
```

Таким образом, ReportGeneratorService теперь открыт для расширения (можно легко добавить новые способы генерации отчетов), но закрыт для изменений (его код не нужно изменять при добавлении новых форматов отчетов). Это обеспечивает гибкость и уменьшает риск внесения ошибок при модификации существующего кода.
