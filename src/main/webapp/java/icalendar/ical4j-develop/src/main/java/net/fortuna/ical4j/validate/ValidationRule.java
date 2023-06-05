package net.fortuna.ical4j.validate;

import net.fortuna.ical4j.util.CompatibilityHints;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Defines a rule for validating iCalendar content. A rule consists of a validation type (e.g. presence/absence of
 * specific content), and one or more identifiers (names) of iCalendar content.
 *
 * For example, a rule might define a test for one or less DTEND properties using the "OneOrLess" validation type
 * and "DTEND" identifier.
 */
public class ValidationRule<T> implements Serializable {

    public enum ValidationType {
        None("The following MUST NOT be present:"),
        One("The following are REQUIRED, but MUST NOT occur more than once:"),
        OneOrLess("The following are OPTIONAL, but MUST NOT occur more than once:"),
        OneOrMore("The following are OPTIONAL, and MAY occur more than once:"),
        OneExclusive("If one is present, ALL others MUST NOT be present:"),
        AllOrNone("If one is present, ALL must be present:"),
        ValueMatch("Value MUST match expression:");

        private final String description;

        ValidationType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private final ValidationType type;

    private final String message;

    private final Predicate<T> predicate;

    private final List<String> instances;

    private final boolean relaxedModeSupported;

    /**
     * @param type rule type
     * @param instances list of identifiers to check (parameter, property, component, etc.)
     */
    public ValidationRule(ValidationType type, String...instances) {
        this(type, (Predicate<T> & Serializable) (T p) -> true, null, instances);
    }

    public ValidationRule(ValidationType type, Predicate<T> predicate, String message, String...instances) {
        this(type, predicate, message, false, instances);
    }

    /**
     * @param type rule type
     * @param relaxedModeSupported indicates if rule can be ignored when relaxed mode is enabled
     * @param instances list of identifiers to check (parameter, property, component, etc.)
     */
    public ValidationRule(ValidationType type, boolean relaxedModeSupported, String...instances) {
        this(type, (Predicate<T> & Serializable) (T p) -> true, relaxedModeSupported, instances);
    }

    public ValidationRule(ValidationType type, Predicate<T> predicate, boolean relaxedModeSupported, String...instances) {
        this(type, predicate, null, relaxedModeSupported, instances);
    }

    public ValidationRule(ValidationType type, Predicate<T> predicate, String message, boolean relaxedModeSupported, String...instances) {
        this.type = type;
        this.predicate = predicate;
        this.message = message;
        this.instances = Arrays.asList(instances);
        this.relaxedModeSupported = relaxedModeSupported;
    }

    public ValidationType getType() {
        return type;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public List<String> getInstances() {
        return instances;
    }

    public String getMessage(String...instances) {
        List<String> match = getInstances();
        if (instances.length > 0) {
            match = Arrays.asList(instances);
        }
        return String.format("%s %s", message != null ? message : getType().getDescription(),
                String.join(",", match));
    }

    public ValidationEntry.Severity getSeverity() {
        boolean warnOnly = CompatibilityHints.isHintEnabled(CompatibilityHints.KEY_RELAXED_VALIDATION)
                && relaxedModeSupported;
        if (warnOnly) {
            return ValidationEntry.Severity.WARNING;
        } else {
            return ValidationEntry.Severity.ERROR;
        }
    }
}
