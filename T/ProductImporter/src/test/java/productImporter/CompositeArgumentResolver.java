package productImporter;

import java.util.Optional;

public class CompositeArgumentResolver implements DomainArgumentsResolver {

    private final DomainArgumentsResolver[] resolvers;

    public CompositeArgumentResolver(DomainArgumentsResolver... resolvers){
        this.resolvers = resolvers;
    }

    @Override
    public Optional<Object> tryResolve(Class<?>[] parameterTypes) {
        for (DomainArgumentsResolver resolver : resolvers) {
            Optional<Object> arguments = resolver.tryResolve(parameterTypes);
            if(arguments.isPresent()) {
                return arguments;
            }
        }
        return Optional.empty();
    }
}
