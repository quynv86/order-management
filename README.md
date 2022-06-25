#### 1. Lazy load and fetch child elements without transaction - Got exception

org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: vn.fis.training.ordermanagement.model.Order.orderItems, could not initialize proxy - no Session
at org.hibernate.collection.internal.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:614)
at org.hibernate.collection.internal.AbstractPersistentCollection.withTemporarySessionIfNeeded(AbstractPersistentCollection.java:218)
at org.hibernate.collection.internal.AbstractPersistentCollection.initialize(AbstractPersistentCollection.java:591)
at org.hibernate.collection.internal.AbstractPersistentCollection.read(AbstractPersistentCollection.java:149)
at org.hibernate.collection.internal.PersistentBag.toString(PersistentBag.java:621)``
``

#### Reslove : Config : spring.jpa.properties.hibernate.enable_lazy_load_no_trans: true
