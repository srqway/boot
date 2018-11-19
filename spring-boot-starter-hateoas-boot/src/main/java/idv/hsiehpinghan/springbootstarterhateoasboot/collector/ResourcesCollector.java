package idv.hsiehpinghan.springbootstarterhateoasboot.collector;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import idv.hsiehpinghan.springbootstarterhateoasboot.resource.CrudResource;
import idv.hsiehpinghan.springbootstarterhateoasboot.resources.CrudResources;

public class ResourcesCollector implements Collector<CrudResource, List<CrudResource>, CrudResources> {

	@Override
	public Supplier<List<CrudResource>> supplier() {
		return () -> {
			return new LinkedList<>();
		};
	}

	@Override
	public BiConsumer<List<CrudResource>, CrudResource> accumulator() {
		return (list, ele) -> {
			list.add(ele);
		};
	}

	@Override
	public BinaryOperator<List<CrudResource>> combiner() {
		return (list_0, list_1) -> {
			list_0.addAll(list_1);
			return list_0;
		};
	}

	@Override
	public Function<List<CrudResource>, CrudResources> finisher() {
		return (list) -> {
			return new CrudResources(list);
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return EnumSet.of(Characteristics.CONCURRENT);
	}

}
