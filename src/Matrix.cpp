#include "Matrix.h"
#include <iostream>

void multiply(double* p_m1, double* p_m2, double* p_result, jint m1_cols, jint m2_cols, jint result_rows) {
	for (size_t row = 0; row < result_rows; row++) {
		size_t pointerCache1 = row * m1_cols;
		size_t pointerCache2 = row * m2_cols;
		for (size_t col = 0; col < m2_cols; col++) {
			double sum = 0.0;
			pointerCache2 += col;
			for (size_t i = 0; i < m1_cols; i++) {
				sum += p_m1[(row * m1_cols + i)] * p_m2[col + i * m2_cols];
			}
			p_result[row * result_rows + col] = sum;
		}
	}
}
JNIEXPORT void JNICALL Java_Matrix_multiplyNative
(JNIEnv* env, jobject, jdoubleArray m1, jdoubleArray m2, jdoubleArray result, jint m1_cols, jint m2_cols, jint result_rows) {
	double *p_m1 = env->GetDoubleArrayElements(m1, NULL);
	double *p_m2 = env->GetDoubleArrayElements(m2, NULL);
	double *p_result = env->GetDoubleArrayElements(result, NULL);

	multiply(p_m1, p_m2, p_result, m1_cols, m2_cols, result_rows);

	env->ReleaseDoubleArrayElements(result, p_result, NULL);
	env->ReleaseDoubleArrayElements(m1, p_m1, NULL);
	env->ReleaseDoubleArrayElements(m2, p_m2, NULL);
}

JNIEXPORT void JNICALL Java_Matrix_powerNative
(JNIEnv* env, jobject, jdoubleArray m, jdoubleArray result, jint m_cols, jint exp) {
	
	double *p_m = env->GetDoubleArrayElements(m, NULL);
	double *p_result = env->GetDoubleArrayElements(result, NULL);
	size_t result_lenght = env->GetArrayLength(result);
	jdouble* temp = new jdouble[result_lenght];

	memcpy(temp, p_m, sizeof(double) * result_lenght);

	size_t i = 1;
	for (; i < exp; i++) {
		if (i % 2 != 0) {
			multiply(p_m, temp, p_result, m_cols, m_cols, m_cols);
		}
		else {
			multiply(p_m, p_result, temp, m_cols, m_cols, m_cols);
		}
	}

	if (i % 2 != 0) {
		memcpy(p_result, temp, sizeof(double) * result_lenght);
	}

	env->ReleaseDoubleArrayElements(m, p_m, NULL);
	env->ReleaseDoubleArrayElements(result, p_result, NULL);

}


