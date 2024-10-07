package codes.tanis.sensapi;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.nameMatching;

public class SensAPIArchitectureTest {

    @Test
    @DisplayName("Hexagonal architecture rules")
    public void hexagonal_architecture_rules() {
        var solporClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("codes.tanis.sensapi");

        var architectureRules = Architectures.onionArchitecture()
            .adapter("infrastructure", "codes.tanis.sensapi.(**).infrastructure..")
            .applicationServices("codes.tanis.sensapi.(**).application..")
            .domainModels("codes.tanis.sensapi.(**).domain..")
            .domainServices("codes.tanis.sensapi.(**).domain..")
            .ignoreDependency(nameMatching("codes.tanis.sensapi.SensAPI"), alwaysTrue())
            .allowEmptyShould(true); // Optional empty layers

        architectureRules.check(solporClasses);
    }

}
