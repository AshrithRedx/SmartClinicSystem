from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib

app = Flask(__name__)

# Load trained model and encoder
model = joblib.load("ensemble_model.pkl")
encoder = joblib.load("label_encoder.pkl")

# Your feature column names here
# List must exactly match what was used in model training: X.columns
symptoms = [
    "fever",
    "nausea",
    "headache",
    "skin_rash",
    "chills",
    "vomiting",
    "fatigue",
    "diarrhea",
    "body_pain",
    "loss_of_appetite"
]
 # Paste X.columns from notebook
symptom_index = {symptom: idx for idx, symptom in enumerate(symptoms)}

# Disease → Specialist mapping
disease_to_specialist = {
    "Paralysis": ["Neurologist", "Physiatrist"],
    "Hypertension": ["Cardiologist", "General Physician"],
    "Hepatitis B": ["Gastroenterologist", "Infectious Disease Specialist"],
    "Impetigo": ["Dermatologist"],
    "Chronic Cholestasis": ["Gastroenterologist", "Hepatologist"],
    "Sepatitis C": ["Gastroenterologist", "Infectious Disease Specialist"],
    "Typhoid": ["General Physician", "Infectious Disease Specialist"],
    "Dimorphic hemorrhoids(piles)": ["Colorectal Surgeon", "General Surgeon"],
    "Vertigo": ["Neurologist", "ENT Specialist"],
    "Cervical Spondylosis": ["Orthopedic", "Physiatrist"],
    "Tuberculosis": ["Pulmonologist", "Infectious Disease Specialist"],
    "Hyperthyroidism": ["Endocrinologist"],
    "malaria": ["Infectious Disease Specialist", "General Physician"],
    "Gastroenteritis": ["Gastroenterologist", "General Physician"],
    "osteoarthritis": ["Orthopedic", "Rheumatologist"],
    "Heart attack": ["Cardiologist", "Emergency Medicine Specialist"],
    "Dengue": ["Infectious Disease Specialist", "Hematologist"],
    "Pneumonia": ["Pulmonologist", "General Physician"],
    "Urinary tract Infection": ["Urologist", "General Physician"],
    "Hypoglycemia": ["Endocrinologist", "General Physician"],
    "Bronchial asthma": ["Pulmonologist", "Allergist"],
    "Arthritis": ["Rheumatologist", "Orthopedic"],
    "Hepatitis D": ["Gastroenterologist"],
    "Hypothyroidism": ["Endocrinologist"],
    "Acne": ["Dermatologist"],
    "GERD": ["Gastroenterologist"],
    "Peptic Ulcer Disease": ["Gastroenterologist", "General Physician"],
    "Psoriasis": ["Dermatologist"],
    "Drug Reaction": ["Allergist", "Dermatologist"],
    "Diabetes": ["Endocrinologist", "General Physician"],
    "Varicose veins": ["Vascular Surgeon", "General Surgeon"],
    "Hepatitis A": ["Gastroenterologist", "General Physician"],
    "Hepatitis E": ["Gastroenterologist", "Infectious Disease Specialist"],
    "Migraine": ["Neurologist"],
    "Allergy": ["Allergist", "Immunologist"],
    "Jaundice": ["Gastroenterologist"],
    "AIDS": ["Infectious Disease Specialist", "Immunologist"],
    "Alcoholic Hepatitis": ["Gastroenterologist", "Hepatologist"]
}




@app.route("/predict", methods=["POST"])
def predict():
    try:
        data = request.json

        input_data = [int(data.get(symptom, 0)) for symptom in symptoms]
        input_array = np.array(input_data).reshape(1, -1)

        # Predict probabilities
        probabilities = model.predict_proba(input_array)[0]

        # Get top 3 predictions
        top_indices = np.argsort(probabilities)[::-1][:3]
        top_diseases = encoder.inverse_transform(top_indices)
        top_probs = [round(probabilities[i] * 100, 2) for i in top_indices]

        # Get specialists
        top_specialists = [
            disease_to_specialist.get(disease, ["General Physician"])
            for disease in top_diseases
        ]

        return jsonify({
            "top_predictions": [
                {
                    "disease": disease,
                    "confidence": confidence,
                    "specialists": specialists
                }
                for disease, confidence, specialists in zip(top_diseases, top_probs, top_specialists)
            ]
        })

    except Exception as e:
        print("❌ Error:", e)
        return jsonify({"error": str(e)}), 500



if __name__ == "__main__":
    app.run(port=5000)
