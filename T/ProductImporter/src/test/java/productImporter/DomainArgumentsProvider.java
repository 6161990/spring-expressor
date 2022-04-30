package productImporter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class DomainArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<DomainArgumentsSource> {

    @Override
    public void accept(DomainArgumentsSource domainArgumentsSource) {

    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        Method method = context.getRequiredTestMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();

        Object[] arguments = new Object[parameterTypes.length];
        DomainArgumentsResolver argumentsResolver = DomainArgumentsResolver.instance;
        for (int i = 0; i < arguments.length; i++) {
            Optional<Object> argument = argumentsResolver.tryResolve(parameterTypes);
            arguments[i] = argument.orElse(null);
        }

        return Arrays.stream(new Arguments[] {Arguments.of(arguments)});
    }

}
