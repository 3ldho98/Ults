package Ults.common.create;

import Ults.common.create.domain.OrderCreator;
import Ults.common.create.domain.OrderDao;

@Repositories(values = {OrderDao.class})
public class OrderCreateContext extends NestedRootContext {

    @Override
    public void initializeRoot() {

        trait(OrderCreator.UltsOrderCreator.class);
    }

    @Override
    public void doExecute() {
        this.<OrderCreator>get(OrderCreator.UltsOrderCreator.class).create();
    }

}