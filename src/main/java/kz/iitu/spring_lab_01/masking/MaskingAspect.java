package kz.iitu.spring_lab_01.masking;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.StringJoiner;

@Aspect
@Component
@Order(0)
public class MaskingAspect {

	private static final Logger log = LoggerFactory.getLogger(MaskingAspect.class);

	static final String MASK = "***";

	@Before("kz.iitu.spring_lab_01.aspect.Pointcuts.accountOperation()")
	public void logMaskedArguments(JoinPoint jp) {
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
		String[] names = signature.getParameterNames();
		Object[] args = jp.getArgs();

		StringJoiner joined = new StringJoiner(", ", "[", "]");
		for (int i = 0; i < args.length; i++) {
			String value = isSensitive(parameterAnnotations[i]) ? MASK : String.valueOf(args[i]);
			joined.add(names[i] + "=" + value);
		}
		log.info("[MASK] -> {} args={}", signature.toShortString(), joined);
	}

	private static boolean isSensitive(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (annotation instanceof Sensitive) {
				return true;
			}
		}
		return false;
	}
}
