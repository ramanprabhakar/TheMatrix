package com.ramanprabhakar.thematrix;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MatrixActivity extends AppCompatActivity {

    Integer matrixSize;
    GridView gridView;
    Integer boxCount;
    int[] numArray;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        matrixSize = getIntent().getIntExtra("INPUT", -1);
        boxCount = matrixSize * matrixSize;

        numArray = new int[boxCount];

        for (int i = 0; i < boxCount; i++) {
            numArray[i] = (int) (Math.random() * 3) + 1;
        }

        gridView = (GridView) findViewById(R.id.gv_matrix);
        gridView.setNumColumns(matrixSize);
        gridView.setHorizontalSpacing(8);
        gridView.setVerticalSpacing(8);

        adapter = new MyAdapter(MatrixActivity.this, numArray);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                makeZero(position, numArray[position]);
            }
        });
    }

    private void makeZero(int clickedPosition, int i) {

        boolean firstColumn = false;
        boolean lastColumn = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean middleColumn = false;
        boolean middleRow = false;
        boolean upperEqual = false;
        boolean lowerEqual = false;
        boolean leftEqual = false;
        boolean rightEqual = false;
        boolean anyNeighbourEqual = false;

        if(matrixSize==1){
            updateSelf(clickedPosition);
            adapter.notifyDataSetChanged();
        }else{
            int columnCheck = (clickedPosition + 1) % matrixSize;
            if (columnCheck == 0) {
                lastColumn = true;
            } else if (columnCheck == 1) {
                firstColumn = true;
            } else {
                middleColumn = true;
            }

            if (clickedPosition + 1 - matrixSize > 0) {
                hasUpper = true;
                if (hasUpper) {
                    if (numArray[clickedPosition - matrixSize] == i) {
                        upperEqual = true;
                    }
                }
            }

            if (clickedPosition + 1 + matrixSize <= boxCount) {
                hasLower = true;
                if (hasLower) {
                    if (numArray[clickedPosition + matrixSize] == i) {
                        lowerEqual = true;
                    }
                }
            }

            if (hasUpper && hasLower) {
                middleRow = true;
            }


            if (middleColumn) {
                if (numArray[clickedPosition + 1] == i) {
                    rightEqual = true;
                }

                if (numArray[clickedPosition - 1] == i) {
                    leftEqual = true;
                }
            } else if (firstColumn) {
                if (numArray[clickedPosition + 1] == i) {
                    rightEqual = true;
                }
            } else if (lastColumn) {
                if (numArray[clickedPosition - 1] == i) {
                    leftEqual = true;
                }
            }


            if (upperEqual || lowerEqual || leftEqual || rightEqual) {
                anyNeighbourEqual = true;
            }

            if (anyNeighbourEqual) {
                if (middleColumn) {
                    if (middleRow) {
                        updateUpper(clickedPosition, i);
                        updateLower(clickedPosition, i);
                        updateRight(clickedPosition, i);
                        updateLeft(clickedPosition, i);
                        updateSelf(clickedPosition);
                    } else if (hasLower) {
                        updateLeft(clickedPosition, i);
                        updateRight(clickedPosition, i);
                        updateLower(clickedPosition, i);
                        updateSelf(clickedPosition);
                    } else if (hasUpper) {
                        updateUpper(clickedPosition, i);
                        updateLeft(clickedPosition, i);
                        updateRight(clickedPosition, i);
                        updateSelf(clickedPosition);
                    }
                } else if (firstColumn) {
                    if (middleRow) {
                        updateUpper(clickedPosition, i);
                        updateLower(clickedPosition, i);
                        updateRight(clickedPosition, i);
                        updateSelf(clickedPosition);
                    } else if (hasLower) {
                        updateRight(clickedPosition, i);
                        updateLower(clickedPosition, i);
                        updateSelf(clickedPosition);
                    } else if (hasUpper) {
                        updateUpper(clickedPosition, i);
                        updateRight(clickedPosition, i);
                        updateSelf(clickedPosition);
                    }
                } else if (lastColumn) {
                    if (middleRow) {
                        updateUpper(clickedPosition, i);
                        updateLower(clickedPosition, i);
                        updateLeft(clickedPosition, i);
                        updateSelf(clickedPosition);
                    } else if (hasLower) {
                        updateLeft(clickedPosition, i);
                        updateLower(clickedPosition, i);
                        updateSelf(clickedPosition);
                    } else if (hasUpper) {
                        updateUpper(clickedPosition, i);
                        updateLeft(clickedPosition, i);
                        updateSelf(clickedPosition);
                    }
                }
                adapter.notifyDataSetChanged();
            }else{
                updateSelf(clickedPosition);
                adapter.notifyDataSetChanged();
            }
        }


    }

    private void updateSelf(int position) {
        numArray[position] = 0;
    }

    private void updateUpper(int position, int value) {
        if (numArray[position - matrixSize] == value) {
            numArray[position - matrixSize] = 0;
        }
    }

    private void updateLower(int position, int value) {
        if (numArray[position + matrixSize] == value) {
            numArray[position + matrixSize] = 0;
        }
    }

    private void updateLeft(int position, int value) {
        if (numArray[position - 1] == value) {
            numArray[position - 1] = 0;
        }
    }

    private void updateRight(int position, int value) {
        if (numArray[position + 1] == value) {
            numArray[position + 1] = 0;
        }
    }

}
