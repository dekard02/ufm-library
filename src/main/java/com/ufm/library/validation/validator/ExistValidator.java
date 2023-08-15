package com.ufm.library.validation.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.ufm.library.validation.annotation.Exist;

@Component
public class ExistValidator implements ConstraintValidator<Exist, Object> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<?> entityClass;
    private String entityPath;
    private String message;

    @Override
    public void initialize(Exist constraintAnnotation) {
        entityClass = constraintAnnotation.entityClass();
        entityPath = constraintAnnotation.entityPath();
        message = constraintAnnotation.message();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery();
        var root = cq.from(entityClass);
        cq.select(cb.count(root))
                .where(cb.equal(
                        root.get(entityPath),
                        cb.literal(value)));

        if (message.contains("{}")) {
            var finalMessage = message.replace("{}", value.toString());
            context.buildConstraintViolationWithTemplate(finalMessage)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return (long) entityManager.createQuery(cq).getSingleResult() > 0;
    }

}
