package gov.va.api.health.deidentification.core;

/**
 * The synthetic data process needs to be provided with a set of names to use when generating fake
 * data. One approach is to use a class path resource file. {@link ClassPathResourceBasedNames}
 */
public interface Names {

  /**
   * The names implementation should provide all logic around acquiring a name from the set, given a
   * seed. This should handle wrapping index bounds.
   */
  String getName(long index);
}
